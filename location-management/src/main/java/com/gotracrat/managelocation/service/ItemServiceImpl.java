package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.dto.*;
import com.gotracrat.managelocation.entity.*;
import com.gotracrat.managelocation.exception.BadRequestException;
import com.gotracrat.managelocation.exception.InternalServerException;
import com.gotracrat.managelocation.exception.ResourceNotFoundException;
import com.gotracrat.managelocation.repository.*;
import com.gotracrat.managelocation.resource.*;
import com.gotracrat.managelocation.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Service implementation for Item.
 *
 * @author prabhakar
 */
@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private VwItemRepository vwItemRepository;

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private AttributevalueRepository attributevalueRepository;

    @Autowired
    private ItemServiceRepository itemServiceRepository;

    @Autowired
    private ItemDAO itemDAOImpl;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private VwGetItemRepository vwGetItemRepository;

    @Autowired
    private UserLogRepository userLogRepository;

    @Autowired
    private RepairlogRepository repairLogRepository;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public ItemResource getItem(Integer itemId) {
        ItemResource itemResource = itemDAOImpl.getItem(itemId);
        List<Attributevalue> attributeValues = attributevalueRepository
                .findByCompositePrimaryKeyEntityidAndCompositePrimaryKeyEntitytypeid(itemId,
                        EntityTypeIDEnum.ITEM_TYPE_ENTITYTYPE_ID.entityTypeID());
        if (attributeValues != null && !attributeValues.isEmpty()) {
            itemResource.setAttributevalues(this.buildAttributeValueResources(attributeValues));
        }
        Date inServiceOn = itemResource.getInserviceon();
        if (inServiceOn == null) {
            itemResource.setDaysinservice((long) 0);
        } else {
            itemResource.setDaysinservice(TimeUnit.MILLISECONDS
                    .toDays(java.util.Calendar.getInstance().getTime().getTime() - inServiceOn.getTime()));
        }
        itemResource.setWarrantyExpirationStr(DateUtils.getDateAsString(itemResource.getWarrantyexpiration()));
        return itemResource;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public String deleteItem(Integer itemId, Integer companyId, UserLog userLog) {
        if (exist(itemId)) {
            itemRepository.deleteItem(itemId);
            return GoTracratConstants.ITEM_DELETED;
        }
        userLog.setCanNotInsert(true);
        throw new ResourceNotFoundException(GoTracratConstants.INVALID_ITEM_ID + itemId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public ItemResource createItem(ItemResource itemResource) {
        if (checkTag(itemResource.getTypeId(), itemResource.getTag())) {
            throw new BadRequestException("Tag already Exists");
        }
        Item savedItem = itemRepository.save(this.buildItem(itemResource));
        if (savedItem.getItemid() != null && savedItem.getItemid() != 0) {
            List<AttributeValueResource> attributeValueResources = itemResource.getAttributevalues();
            if (attributeValueResources != null && !attributeValueResources.isEmpty()) {
                List<Attributevalue> attributeValues = buildAttributeValues(attributeValueResources, savedItem);

                if (!attributeValues.isEmpty()) {
                    attributevalueRepository.saveAll(attributeValues);
                }
            }
            if (itemResource.getAttachmentList() != null && !itemResource.getAttachmentList().isEmpty()) {
                createItemAttachments(itemResource.getAttachmentList(), savedItem);
            }
        }
        return itemResource;
    }

    private boolean checkTag(Integer typeId, String tag) {
        List<String> tagsList = itemRepository.findByAsList(tag, typeId, PageRequest.of(0, 10));
        return !tagsList.isEmpty();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateItem(ItemResource itemResource) {
        if (exist(itemResource.getItemid())) {
            Item item = this.buildItem(itemResource);
            Optional<List<AttributeValueResource>> attributeValueResourcesOp = Optional
                    .ofNullable(itemResource.getAttributevalues());

            if (attributeValueResourcesOp.isPresent()) {
                List<Attributevalue> attributeValues = buildAttributeValues(attributeValueResourcesOp.get(), item);
                if (!attributeValues.isEmpty()) {
                    attributevalueRepository.saveAll(attributeValues);
                }
            }
            if (itemResource.getAttachmentList() != null && !itemResource.getAttachmentList().isEmpty()) {
                try {
                    updateItemAttachments(itemResource.getAttachmentList(), itemResource.getItemid());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            itemRepository.save(item);
            return;
        }
        throw new ResourceNotFoundException(GoTracratConstants.ITEM_NOT_FOUND + itemResource.getItemid());
    }


    private boolean exist(Integer itemId) {
        return itemRepository.existsById(itemId);
    }


    private Item buildItem(ItemResource itemResource) {
        Item item = new Item();
        BeanUtils.copyProperties(itemResource, item);

        Location location = new Location();
        location.setLocationid(itemResource.getLocationid());
        item.setLocation(location);

        Status status = new Status();
        status.setStatusid(itemResource.getStatusid());
        item.setStatus(status);
        item.setItemTypeId(itemResource.getTypeId());

        if (itemResource.getWarrantytypeid() != null && itemResource.getWarrantytypeid() != 0) {
            WarrantyType warrantytype = new WarrantyType();
            warrantytype.setWarrantytypeid(itemResource.getWarrantytypeid());
            item.setWarrantytype(warrantytype);
        }

        return item;
    }

    private List<Attributevalue> buildAttributeValues(List<AttributeValueResource> attributeValueResources, Item item) {
        List<Attributevalue> attributeValues = new ArrayList<>();
        // parallel stream java8 for good performance and less time than sequential
        // stream
        attributeValueResources.parallelStream().forEach(attributeValueResource -> {
            Attributevalue attributevalue = new Attributevalue();
            BeanUtils.copyProperties(attributeValueResource, attributevalue);
            attributevalue.setEntityid(item.getItemid());
            attributevalue.setEntitytypeid(EntityTypeIDEnum.ITEM_TYPE_ENTITYTYPE_ID.entityTypeID());

            AttributevalueKey compositePrimaryKey = new AttributevalueKey();
            Attributename attributename = new Attributename();
            attributename.setAttributenameid(attributeValueResource.getAttributename().getAttributenameid());
            BeanUtils.copyProperties(attributeValueResource.getAttributename(), attributename);
            compositePrimaryKey.setAttributename(attributename);
            compositePrimaryKey.setEntityid(item.getItemid());
            compositePrimaryKey.setEntitytypeid(EntityTypeIDEnum.ITEM_TYPE_ENTITYTYPE_ID.entityTypeID());
            attributevalue.setCompositePrimaryKey(compositePrimaryKey);

            attributeValues.add(attributevalue);
        });
        return attributeValues;
    }

    private List<AttributeValueResource> buildAttributeValueResources(List<Attributevalue> attributeValues) {
        List<AttributeValueResource> attributeValueResourceList = new ArrayList<>();
        attributeValues.parallelStream().forEach(attributeValue -> {
            AttributeValueResource attributevalueResource = new AttributeValueResource();
            BeanUtils.copyProperties(attributeValue, attributevalueResource);
            attributevalueResource.setEntityid(attributeValue.getEntityid());
            attributevalueResource.setEntitytypeid(attributeValue.getEntitytypeid());
            AttributeNameResource attributenameResource = new AttributeNameResource();
            Attributename attributeName = attributeValue.getCompositePrimaryKey().getAttributename();
            BeanUtils.copyProperties(attributeName, attributenameResource);
            attributenameResource.setAttributenameid(attributeName.getAttributenameid());
            if (attributeName.getAttributelistitem() != null) {
                List<AttributeListItemResource> attributeListItemList = attributeName.getAttributelistitem().stream().map(attributelistitem -> {
                    AttributeListItemResource attributelistitemResource = new AttributeListItemResource();
                    BeanUtils.copyProperties(attributelistitem, attributelistitemResource);
                    return attributelistitemResource;
                }).collect(Collectors.toList());
                attributenameResource.setAttributelistitemResource(attributeListItemList);
            }
            Attributetype attributetype = attributeValue.getCompositePrimaryKey().getAttributename().getAttributetype();
            AttributeTypeResource attributetypeResource = new AttributeTypeResource();
            BeanUtils.copyProperties(attributetype, attributetypeResource);
            attributenameResource.setAttributetype(attributetypeResource);
            Type type = attributeValue.getCompositePrimaryKey().getAttributename().getType();
            TypeResource typeResource = new TypeResource();
            BeanUtils.copyProperties(type, typeResource);
            attributenameResource.setType(typeResource);
            attributevalueResource.setAttributename(attributenameResource);
            attributeValueResourceList.add(attributevalueResource);
        });
        return attributeValueResourceList;
    }

    @Override
    public List<String> searchTags(String tagName, Integer companyId) {
        return vwItemRepository.findSuggestions(tagName, companyId, PageRequest.of(0, 10));
    }

    @Override
    public List<String> searchTagsByType(String tagName, Integer typeId) {
        return itemRepository.findByAsList(tagName, typeId, PageRequest.of(0, 10));
    }

    @Override
    @Transactional
    public void updateDefaultImageForItem(Integer itemId, Integer attachmentId) {
        if (exist(itemId)) {
            itemRepository.setDefaultAttachmentId(attachmentId, itemId);
            return;
        }
        throw new ResourceNotFoundException(GoTracratConstants.ITEM_NOT_FOUND + itemId);
    }

    @Override
    public List<FindReplacementDTO> getAllItemAttributesForReplacement(Integer itemId) {
        return itemDAOImpl.getAllItemAttributesForReplacement(itemId);
    }

    @Override
    public List<ChangeLogDTO> getChangeLog(Integer itemId) {
        return journalRepository.getChangeLogData(itemId, EntityTypeIDEnum.ITEM_TYPE_ENTITYTYPE_ID.entityTypeID(),
                JournalTypeIDEnum.CHANGELOG_JOURNALTYPE_ID.typeID(), PageRequest.of(0, 3));

    }

    @Override
    public Map<String, List<ItemResource>> searchItems(ItemResource itemResource, Integer companyId,
                                                       Boolean isOwnerAdmin, String userId) {
        List<ItemResource> itemResourceList = itemDAOImpl.searchItems(itemResource, companyId, isOwnerAdmin, userId);
        return itemResourceList.stream()
                .collect(Collectors.groupingBy(ItemResource::getTypeName)).entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, TreeMap::new));
    }

    @Override
    public WarehouseTagDTO getWarehouseTag(Integer itemId) {
        List<WarehouseTagDTO> warehouseTag = itemRepository.getWarehouseTag(itemId, PageRequest.of(0, 1));
        if (!warehouseTag.isEmpty()) {
            return warehouseTag.get(0);
        }
        return new WarehouseTagDTO();
    }

    @Override
    public Map<String, Map<String, List<MasterSearchResponseDTO>>> masterSearch(
            MasterSearchRequestDTO masterSearchRequestDTO) {
        Map<String, Map<String, List<MasterSearchResponseDTO>>> items = new TreeMap<>();
        List<MasterSearchResponseDTO> searchItems = itemDAOImpl.masterSearch(masterSearchRequestDTO);
        if (searchItems != null) {
            searchItems = buildAttributesFromXmlForMasterSearch(searchItems);
            Map<String, List<MasterSearchResponseDTO>> itemsWithCompanyNameAsKey = searchItems.stream()
                    .collect(Collectors.groupingBy(MasterSearchResponseDTO::getCompanyName));
            itemsWithCompanyNameAsKey.forEach((companyName, itemList) -> {
                Map<String, List<MasterSearchResponseDTO>> itemsWithTypeAsKey = itemList.stream()
                        .collect(Collectors.groupingBy(MasterSearchResponseDTO::getTypeName));
                Map<String, List<MasterSearchResponseDTO>> sortedItems = new TreeMap<>(itemsWithTypeAsKey);
                items.put(companyName, sortedItems);
            });
        }
        return items;
    }

    private List<MasterSearchResponseDTO> buildAttributesFromXmlForMasterSearch(
            List<MasterSearchResponseDTO> itemResources) {
        List<MasterSearchResponseDTO> items = new ArrayList<>();
        itemResources.forEach(item -> {
            if (item.getAttributesXml() != null) {
                JAXBContext jaxbContext;
                try {
                    jaxbContext = JAXBContext.newInstance(AttributesXml.class);
                    Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
                    AttributesXml attributes = (AttributesXml) jaxbUnMarshaller
                            .unmarshal(new StringReader(item.getAttributesXml()));
                    item.setAttributeNameList(attributes.getAttributesList());
                    items.add(item);
                } catch (JAXBException exception) {
                    log.error(GoTracratConstants.ERROR, exception);
                }
            }
        });
        return items;
    }


    private List<MasterSearchWithAttributesResponseDTO> buildAttributesFromXmlForMasterSearchAttributes(
            List<MasterSearchWithAttributesResponseDTO> itemResources) {
        List<MasterSearchWithAttributesResponseDTO> items = new ArrayList<>();
        itemResources.forEach(item -> {
            if (item.getAttributesXml() != null) {
                JAXBContext jaxbContext;
                try {
                    jaxbContext = JAXBContext.newInstance(MasterSearchXml.class);
                    Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
                    MasterSearchXml attributes = (MasterSearchXml) jaxbUnMarshaller
                            .unmarshal(new StringReader(item.getAttributesXml()));
                    item.setAttributes(attributes.getAttributesList());
                    items.add(item);
                } catch (JAXBException exception) {
                    log.error(GoTracratConstants.ERROR, exception);
                }
            } else {
                item.setAttributes(new ArrayList<>());
                items.add(item);
            }
        });
        return items;
    }


    public void createItemAttachments(List<AttachmentResource> attachmentResourceList, Item item) {
        Integer entityId = item.getItemid();
        final boolean[] updateDefaultAttachmentId = {true};
        attachmentResourceList.stream().forEach(attachmentResource -> {
            Attachment attachment = new Attachment();
            BeanUtils.copyProperties(attachmentResource, attachment);
            attachment.setEntitytypeid(EntityTypeIDEnum.ITEM_TYPE_ENTITYTYPE_ID.entityTypeID());
            attachment.setEntityid(entityId);
            byte[] compressed = new byte[0];
            try {
                compressed = GotracratUtility.compress(attachmentResource.getAttachmentFile());
            } catch (IOException e) {
                throw new InternalServerException(e);
            }
            attachment.setAttachment(compressed);
            attachment.setIsNew(true);
            attachment.setAttachmentid(0);
            if (updateDefaultAttachmentId[0]) {
                Attachment savedAttachment = attachmentRepository.save(attachment);
                itemRepository.setDefaultAttachmentId(savedAttachment.getAttachmentid(), entityId);
                updateDefaultAttachmentId[0] = false;
            } else {
                attachmentRepository.save(attachment);
            }
        });
    }

    private void updateItemAttachments(List<AttachmentResource> attachmentList, Integer itemId) {
        attachmentList.stream().forEach(attachmentResource -> {
            Attachment attachment = new Attachment();
            BeanUtils.copyProperties(attachmentResource, attachment);
            attachment.setEntitytypeid(EntityTypeIDEnum.ITEM_TYPE_ENTITYTYPE_ID.entityTypeID());
            attachment.setEntityid(itemId);
            byte[] compressed = new byte[0];
            try {
                compressed = GotracratUtility.compress(attachmentResource.getAttachmentFile());
            } catch (IOException exception) {
                throw new InternalServerException(exception);
            }
            attachment.setAttachment(compressed);
            attachment.setIsNew(true);
            attachment.setAttachmentid(0);
            attachmentRepository.save(attachment);
        });
    }


    @Override
    public Map<String, List<SearchResponseDTO>> searchItems(ItemSearchRequestDTO request) {
        Map<String, List<SearchResponseDTO>> items = new HashMap<>();
        List<SearchResponseDTO> itemResources = itemDAOImpl.searchItems(request);
        if (!itemResources.isEmpty()) {
            itemResources = buildAttributesFromXml(itemResources);
            items = itemResources.stream().collect(Collectors.groupingBy(SearchResponseDTO::getTypeName));
            items = new TreeMap<>(items);
        }
        return items;
    }

    private List<SearchResponseDTO> buildAttributesFromXml(List<SearchResponseDTO> itemResourceList) {
        List<SearchResponseDTO> items = new ArrayList<>();
        itemResourceList.forEach(item -> {
            if (item.getAttributesXml() != null) {
                JAXBContext jaxbContext;
                try {
                    jaxbContext = JAXBContext.newInstance(AttributesXml.class);
                    Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
                    AttributesXml attributes = (AttributesXml) jaxbUnMarshaller
                            .unmarshal(new StringReader(item.getAttributesXml()));
                    item.setAttributeNameList(attributes.getAttributesList());
                    items.add(item);
                } catch (JAXBException exception) {
                    log.error(GoTracratConstants.ERROR, exception);
                }
            }
        });
        return items;

    }

    @Override
    public VwGetItem getItemById(Integer itemId) {
        Optional<VwGetItem> itemOptional = vwGetItemRepository.findById(itemId);
        if (itemOptional.isPresent()) {
            VwGetItem item = itemOptional.get();
            Date inServiceOn = item.getInServiceOn();
            if (inServiceOn == null) {
                item.setDaysInService((long) 0);
            } else {
                item.setDaysInService(ChronoUnit.DAYS.between(
                        inServiceOn.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), LocalDateTime.now()));
            }
            Date serviceDate = itemServiceRepository.findServiceDateByItemId(itemId);
            if (serviceDate == null) {
                item.setDaysDueForNextService((long) 0);
            } else {
                item.setServiceDate(serviceDate);
                item.setDaysDueForNextService(ChronoUnit.DAYS.between(LocalDateTime.now(),
                        serviceDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
            }
            return buildAttributesFromXml(item);
        }
        throw new ResourceNotFoundException(GoTracratConstants.ITEM_NOT_FOUND + itemId);
    }

    @Override
    public List<MasterSearchWithAttributesResponseDTO> masterSearchWithAttributes(MasterSearchWithAttributesRequestDTO masterSearchWithAttributesRequestDTO) {
        masterSearchWithAttributesRequestDTO.setAttributesXml(attributeXmlString(masterSearchWithAttributesRequestDTO.getAttributes()));
        List<MasterSearchWithAttributesResponseDTO> searchItems = itemDAOImpl.masterSearchWithAttributes(masterSearchWithAttributesRequestDTO);
        if (searchItems != null) {
            searchItems = buildAttributesFromXmlForMasterSearchAttributes(searchItems);
        }
        return searchItems;
    }

    private String attributeXmlString(List<AttributeDTO> attributeNameList) {
        String attributeString = null;
        if (attributeNameList != null && !attributeNameList.isEmpty()) {
            StringBuilder xmlBuilder = null;
            if (!attributeNameList.isEmpty()) {
                xmlBuilder = new StringBuilder("<AttributeList>");
                for (AttributeDTO attributeDTO : attributeNameList) {
                    xmlBuilder.append("<Attribute>");
                    xmlBuilder.append("<AttributeName>" + attributeDTO.getAttributeName() + "</AttributeName>");
                    xmlBuilder.append("<AttributeValue>" + attributeDTO.getValue() + "</AttributeValue>");
                    if (attributeDTO.getAttributeName().equalsIgnoreCase("RPM")) {
                        xmlBuilder.append("<LookUpCode>RPM</LookUpCode>");
                    } else {
                        xmlBuilder.append("<LookUpCode>EM</LookUpCode>");
                    }
                    xmlBuilder.append("</Attribute>");
                }
                xmlBuilder.append("</AttributeList>");
            }

            attributeString = new String(xmlBuilder);
        }
        return attributeString;

    }

    private VwGetItem buildAttributesFromXml(VwGetItem item) {
        if (item.getAttributesXml() != null) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(AttributesXml.class);
                Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
                AttributesXml attributes = (AttributesXml) jaxbUnMarshaller
                        .unmarshal(new StringReader(item.getAttributesXml()));
                item.setAttributeValues(attributes.getAttributesList());
            } catch (JAXBException exception) {
                log.error(GoTracratConstants.ERROR, exception);
            }
        }
        return item;
    }

    @Override
    public String createItemRepairUsingGoForm(GoFormResource goFormResource) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("scope", "public_api");
        map.add("grant_type", "client_credentials");
        map.add("client_id", "1ee9f3f1-6f1a-418a-a456-a6712ce786f8");
        map.add("client_secret", "70883e8f-5813-4899-b564-1eb6d1b827f8");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<OAuthToken> response = restTemplate().exchange("https://accounts.goformz.com/connect/token",
                HttpMethod.POST,
                entity,
                OAuthToken.class);
        System.out.println(response.getBody().getAccess_token());
        HttpHeaders headers1 = new HttpHeaders();
        headers1.setContentType(MediaType.APPLICATION_JSON);
        headers1.set("Authorization", "Bearer " + response.getBody().getAccess_token());
        HttpEntity<String> entity1 = new HttpEntity<>(headers1);
        ResponseEntity<String> s = restTemplate().exchange("https://api.goformz.com/v2/formz/4c49f171-cf19-4be7-817d-1ea6e4f06dc1", HttpMethod.GET, entity1, String.class);
    //    JSONObject jsonObject = new JSONObject(s.getBody());
   //     System.out.println(jsonObject.getJSONObject("fields").get("FinalLeads_Note6"));
        UserLog userLog = new UserLog();
        userLog.setActionComment("GoForms API Hitted");
        userLog.setCompanyID(453);
        userLog.setUserName("ypatel");
        userLog.setUserAction("ITEM");
        userLog.setLogDate(new Date());
        userLogRepository.save(userLog);
        return "goForms api Hitted";
    }

    @Override
    public Map<String,List<FailedItemsResource>> getFailedItemsTwiceInYear(Integer companyId) {
        String startDate = DateUtils.getLastYearDateFromToday();
        String endDate = DateUtils.getTodayDate();
        List<FailedItemsResource> failedItems = itemDAOImpl.getFailedItemsTwiceInYear(companyId,startDate,endDate);
        failedItems.forEach(item -> {
            if (item.getAttributesXml() != null) {
                JAXBContext jaxbContext;
                try {
                    jaxbContext = JAXBContext.newInstance(AttributesXml.class);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    AttributesXml attributes = (AttributesXml) jaxbUnmarshaller
                            .unmarshal(new StringReader(item.getAttributesXml()));
                    item.setAttributeNameList(attributes.getAttributesList());
                } catch (JAXBException exception) {
                    log.error(GoTracratConstants.ERROR, exception);
                }
            }
        });
        Map<String, List<FailedItemsResource>> resultsMap = failedItems.stream()
                .collect(Collectors.groupingBy(FailedItemsResource::getTypeName));
        return new TreeMap<>(resultsMap);
    }
}
