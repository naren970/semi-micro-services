package com.gotracrat.managelocation.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import com.gotracrat.managelocation.dto.*;
import com.gotracrat.managelocation.entity.*;
import com.gotracrat.managelocation.resource.*;
import org.springframework.util.LinkedCaseInsensitiveMap;

public class MockUtils {


    public static ItemResource mockItemResource() {
        return new ItemResource(0, 366504, "MM00008", "Motor", 3707, 2457, 0, null, null);
    }

    /*
     * public static ItemResource mockItemResourceUpdate() { return new
     * ItemResource(0, 366504, "MM00008", 3707, 2457,0, null, null); }
     */
    public static ItemResource mockItemResourceForCreateAndUpdate(boolean flag) {
        AttributeNameResource attributenameResource = new AttributeNameResource();
        attributenameResource.setAttributenameid(47061);
        AttributeValueResource attributevalueResource = new AttributeValueResource(attributenameResource, 0, 2, "1",
                "justin");
        List<AttributeValueResource> attributeValues = Arrays.asList(attributevalueResource);
        if (flag) {
            List<AttachmentResource> attachmentList = Arrays.asList(
                    new AttachmentResource("picture.jpg", "application/jpg", "iVBORw0KGgoAAAANSUhEUgAAAyIAAAKEC",
                            "defaultimage", new Date(), "Justin"),
                    new AttachmentResource("picture1.jpg", "application/jpg", "iVBORw0KGgoAAAANSUhEUgAAAyIAAAKEC",
                            "Image 2", new Date(), "Justin"));
            return new ItemResource(0, 366504, "MM00008", "Motor", 3707, 2457, 1, attributeValues, attachmentList);
        } else {
            return new ItemResource(389957, 366504, "MM00008", "Motor", 3707, 2457, 0, attributeValues, null);
        }
    }

    public static UserLog mockUserLog() {
        return new UserLog("ypatel", 279, "ITEM", "ITEM Motor:MM00008 created By ypatel", new Date());
    }

    public static List<ChangeLogDTO> mockChangeLog() {
        return Arrays.asList(
                new ChangeLogDTO(1, 389957, "Automatic Entry - Item Updated on 04/28/2021 by justin", "justin",
                        new Date()),
                new ChangeLogDTO(2, 389957, "Item Updated", "manikanta", new Date()),
                new ChangeLogDTO(3, 389957, "Item Updated", "manikanta", new Date()));
    }

    public static Optional<VwGetItem> mockVwGetItem(boolean flag) {
        Optional<VwGetItem> item = Optional.empty();
        if (flag) {
            LocalDateTime now = LocalDateTime.now();
            Date inserviceon = Date.from(now.minusDays(10).atZone(ZoneId.systemDefault()).toInstant());
            System.out.println(inserviceon);
            item = Optional.of(new VwGetItem(389957, 366504, "MM00008", "test item", 3707, inserviceon, "manikanta",
                    2457, getAttributeXml()));
        } else {
            item = Optional
                    .of(new VwGetItem(389957, 366504, "MM00008", "test item", 3707, null, "manikanta", 2457, null));
        }

        return item;
    }

    private static String getAttributeXml() {

        return "<Attributes\r\n" + "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n"
                + "	<AttributeName>\r\n" + "		<Name>HP/KW/KVA</Name>\r\n"
                + "		<AttributeNameID>39789</AttributeNameID>\r\n" + "		<Value>7.5</Value>\r\n"
                + "	</AttributeName>\r\n" + "	<AttributeName>\r\n" + "		<Name>RPM</Name>\r\n"
                + "		<AttributeNameID>38819</AttributeNameID>\r\n" + "		<Value>1725</Value>\r\n"
                + "	</AttributeName>\r\n" + "	</Attributes>\r\n" + "";
    }

    public static Date getServiceDate() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(Date.from(now.plusDays(10).atZone(ZoneId.systemDefault()).toInstant()));
        return Date.from(now.plusDays(10).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static List<String> mockTagAvailability() {
        return Arrays.asList("MM00008");
    }

    public static List<String> mockTagSuggestions() {
        return Arrays.asList("MM0012", "MM0013", "MM0014");
    }

    public static List<WarehouseTagDTO> mockWareHouseTag() {
        return Arrays.asList(new WarehouseTagDTO(new Date(), "ABC Vendor"));
    }

    public static MasterSearchRequestDTO mockMasterSearchRequest() {
        return new MasterSearchRequestDTO("MM00008");
    }

    public static List<MasterSearchResponseDTO> mockMasterSearch() {

        return Arrays.asList(
                new MasterSearchResponseDTO("MM00008", 389957, "ABC Power Company", "Motor", getAttributeXml()));
    }

    public static List<FindReplacementDTO> mockFindReplacementDTO() {

        return Arrays.asList(new FindReplacementDTO("Enclosure", "TEFC"), new FindReplacementDTO("RPM", "320"));
    }

    public static Item mockItem() {
        Item item = new Item();
        item.setItemid(123);
        return item;
    }

    public static List<Attributevalue> mockAttributeValue() {
        Attributetype attributetype = new Attributetype();
        attributetype.setAttributetypeid(1);
        Type type = new Type();
        type.setTypeid(2457);
        Attributename attributeName = new Attributename();
        attributeName.setAttributenameid(47061);
        attributeName.setAttributetype(attributetype);
        attributeName.setType(type);
        Attributelistitem attributelistitem=new Attributelistitem();
        attributelistitem.setAttributelistvaluesid(1);
        List<Attributelistitem> attributeListItems=Arrays.asList(attributelistitem);
        attributeName.setAttributelistitem(attributeListItems);
        AttributevalueKey attributevalueKey = new AttributevalueKey(attributeName, 389957, 2);
        return Arrays.asList(
                new Attributevalue(attributevalueKey, "1.5", "justin"));
    }

    public static List<ItemResource> mockListItemResource() {
        return Arrays.asList(
                new ItemResource(389957, 366504, "MM00008", "Motor", 3707, 2457, 0, null, null));
    }

    public static List<SearchResponseDTO> mockSearchResponseDTO() {
        return Arrays.asList(
                new SearchResponseDTO(389957, "MM00008", "inservice", "Motor", "test", "testpath", 2));
    }

    public static ItemSearchRequestDTO mockSearchRequestDTO() {

        return new ItemSearchRequestDTO(50420, 366, "MM00008", 3707, 2457, true, "");
    }

    public static List<Location> getLocationsByCompanyId() {
        Company company = new Company();
        company.setCompanyid(453);
        return Arrays.asList(new Location(1, company, null, null, "hyd", "abc", 1, "anudeep", BigDecimal.valueOf(5), true, true, null,
                "hyd1", "hyd2", "Hyd", "TS", "589632"), new Location(2, company, null, null, "hyd", "abc", 1, "anudeep", BigDecimal.valueOf(5), true, true, null,
                "hyd1", "hyd2", "Hyd", "TS", "589632"));
    }

    public static List<LocationResource> getAllLocationsWithHierarchyByCompanyId() {
        CompanyResource companyResource = new CompanyResource();
        companyResource.setCompanyid(453);
        LocationResource locationResource = new LocationResource();
        locationResource.setLocationid(1);
        return Arrays.asList(new LocationResource(1, companyResource, null, "Hyderabad", "abc", 1, "anudeep", BigDecimal.valueOf(5), true, true, null, "hyd1", "hyd2", "HYD", "TS", "123456",
                        453, 1, 1, null, null, 3, 452),
                new LocationResource(2, companyResource, null, "Hyderabad", "abc", 1, "anudeep", BigDecimal.valueOf(5), true, true, null, "hyd1", "hyd2", "HYD", "TS", "123456",
                        453, 1, 1, null, null, 3, 453));
    }

    public static List<LocationResource> mockLocationsForCreateAndUpdate() {
        List<LocationResource> locationResources = new ArrayList<>();
        LocationResource locationResource = new LocationResource();
        locationResource.setLocationid(1);
        locationResource.setCompanyID(453);
        locationResource.setAddress1("Hyd1");
        locationResource.setAddress2("Hyd2");
        locationResource.setCity("HYD");
        locationResource.setState("TS");
        locationResource.setCriticalflag(true);
        locationResource.setAttributevalues(null);
        locationResource.setDesiredspareratio(BigDecimal.valueOf(5));
        locationResource.setDescription("abc");
        locationResource.setName("Hyderabad");
        locationResource.setPostalcode("123456");
        LocationResource locationResourceTwo = new LocationResource();
        locationResource.setLocationid(2);
        locationResource.setCompanyID(453);
        locationResource.setAddress1("Hyd1");
        locationResource.setAddress2("Hyd2");
        locationResource.setCity("HYD");
        locationResource.setState("TS");
        locationResource.setCriticalflag(true);
        locationResource.setAttributevalues(null);
        locationResource.setDesiredspareratio(BigDecimal.valueOf(5));
        locationResource.setDescription("abc");
        locationResource.setName("Hyderabad");
        locationResource.setPostalcode("123456");
        locationResources.add(locationResource);
        locationResources.add(locationResourceTwo);
        return locationResources;
    }

    public static LocationMergeDTO mergeLocations() {
        LocationMergeDTO locationMergeDTO = new LocationMergeDTO();
        locationMergeDTO.setNewLocationId(3);
        locationMergeDTO.setNewLocationName("Vizag");
        locationMergeDTO.setOldLocationId(1);
        return locationMergeDTO;
    }

    public static List<WarrantyTypeResource> getAllWarrantyTypeByCompanyId() {
        return Arrays.asList(new WarrantyTypeResource(1, 453, "repair", "ypatel"),
                new WarrantyTypeResource(2, 453, "motor", "ypatel"));
    }

    public static WarrantyTypeResource mockWarrantyTypeForCreateAndUpdate() {
        WarrantyTypeResource warrantyTypeResource = new WarrantyTypeResource();
        warrantyTypeResource.setCompanyid(453);
        warrantyTypeResource.setWarrantytypeid(1);
        warrantyTypeResource.setWarrantytype("repair");
        warrantyTypeResource.setUserName("ypatel");
        return warrantyTypeResource;
    }

    public static ItemServiceResource getItemService() {
        return new ItemServiceResource(1, 123, new Date(), "motorRepair", true, new Date(), "ypatel", new Date(), "ypatel", new Date(), "ypatel");
    }

    public static ItemServicesDTO getServices() {
        ItemServicesDTO itemServicesDTO = new ItemServicesDTO();
        List<ItemService> completed = Arrays.asList(new ItemService(1, 123, new Date(), "motorRepair", true, new Date(), "ypatel", new Date(), "ypatel", new Date(), "ypatel"),
                new ItemService(2, 123, new Date(), "wire", true, new Date(), "ypatel", new Date(), "ypatel", new Date(), "ypatel"));
        List<ItemService> inCompleted = Arrays.asList(new ItemService(3, 123, new Date(), "motorRepair", false, new Date(), "ypatel", new Date(), "ypatel", new Date(), "ypatel"),
                new ItemService(4, 123, new Date(), "wire", false, new Date(), "ypatel", new Date(), "ypatel", new Date(), "ypatel"));
        itemServicesDTO.setCompletedServices(completed);
        itemServicesDTO.setInCompletedServices(inCompleted);
        return itemServicesDTO;
    }

    public static ItemServiceResource mockItemServiceForCreateAndUpdate() {
        ItemServiceResource itemServiceResource = new ItemServiceResource();
        itemServiceResource.setServiceId(1);
        itemServiceResource.setItemId(123);
        itemServiceResource.setActualCompletion(new Date());
        itemServiceResource.setComplete(true);
        itemServiceResource.setServiceCause("wire");
        itemServiceResource.setServiceDate(new Date());
        itemServiceResource.setCompletedBy("ypatel");
        itemServiceResource.setCreatedDate(new Date());
        itemServiceResource.setUpdatedBy("ypatel");
        itemServiceResource.setUpdatedDate(new Date());
        return itemServiceResource;
    }

    public static DashboardResource getDashBoardData() {
        List<RecentRepairResource> repairResource = new ArrayList<>();
        Map<String, List<RecentItemsResource>> recentItemResource = new HashMap<>();
        List<NotesResource> notesResource = new ArrayList<>();
        Map<String, List<String>> attributeNameMap = new HashMap<>();
        List<String> AnnouncementList = new ArrayList<>();
        return new DashboardResource(453, 1, repairResource, recentItemResource, notesResource, attributeNameMap, AnnouncementList);
    }

    public static List<RecentRepairResource> getAllRepairsInDashboard() {
        return Arrays.asList(new RecentRepairResource());/*(1, 1, "123", "motor", "123", "234", "Hyd", "abc", BigDecimal.valueOf(5), "28-06-2021", 1, true, new Date(), new Date(), null, null),
                new RecentRepairResource(2, 2, "123", "motor", "123", "234", "Hyd", "abc", BigDecimal.valueOf(5), "28-06-2021", 1, true, new Date(), new Date(), null, null));*/
    }

    public static AllRepairsDTO mockAllRepairs() {
        AllRepairsDTO allRepairsDTO = new AllRepairsDTO();
        allRepairsDTO.setCompanyId(453);
        allRepairsDTO.setEndDate("28-06-2021");
        allRepairsDTO.setIsOwnerAdmin("true");
        allRepairsDTO.setStartDate("28-06-2021");
        allRepairsDTO.setStatusFlag(1);
        allRepairsDTO.setTimeFrame("Last Year");
        allRepairsDTO.setUserId("1");
        return allRepairsDTO;
    }

    public static Map<String, BigDecimal> getDashboardResultsByTimeFrame() {
        Map<String, BigDecimal> percentageMap = new LinkedHashMap<>();
        percentageMap.put("Electrical", BigDecimal.valueOf(5));
        percentageMap.put("Wire", BigDecimal.valueOf(2));
        return percentageMap;

    }

    public static FailureCauseDTO mockFailureCauses() {
        FailureCauseDTO failureCauseDTO = new FailureCauseDTO();
        failureCauseDTO.setCompanyId(453);
        failureCauseDTO.setEndDate("28-06-2021");
        failureCauseDTO.setIsOwnerAdmin("true");
        failureCauseDTO.setStartDate("28-06-2021");
        failureCauseDTO.setLocationId(1);
        failureCauseDTO.setIsByRepairCost("true");
        failureCauseDTO.setTimeFrame("Last Year");
        failureCauseDTO.setUserId("1");
        return failureCauseDTO;
    }

    public static UserSecurityResource getUserSecurity() {
        return new UserSecurityResource("1", "1", 1, 2, "ypatel");
    }

    public static List<UserLevelsDTO> getLevelsByUserName() {
        return Arrays.asList(new UserLevelsDTO("1", "admin"));
    }

    public static List<VwUserSecurity> getAllRolesForUser() {
        return Arrays.asList(new VwUserSecurity(new UsersecurityKey("1", 453, 1), "abc", "1", "hyd", "abc", "1", "admin", "Tester", 1));
    }

    public static List<UserRolesDTO> getRolesForALoggedInUser() {
        return Arrays.asList(new UserRolesDTO("Admin", 1));
    }

    public static Optional<UserSecurity> mockGetUserSecurity() {
        return Optional.of(new UserSecurity(new UsersecurityKey("1", 453, 1), "1", "ypatel"));
    }

    public static UserSecurityResource createOrUpdateUserSecurityResource() {
        return new UserSecurityResource("1", "1", 453, 1, "ypatel");
    }

    public static UserSecurity createOrUpdateUserSecurity() {
        return new UserSecurity(new UsersecurityKey("1", 453, 1), "1", "ypatel");
    }

    public static List<UserLevelsDTO> mockGetLevels() {
        return Arrays.asList(new UserLevelsDTO("1", "admin"));
    }

    public static List<VwUserSecurity> mockGetAllRolesForUser() {
        return Arrays.asList(new VwUserSecurity(new UsersecurityKey("1", 453, 1),"abc", "1", "hyd", "abc", "1", "admin", "Tester", 1));
    }

    public static List<UserRolesDTO> mockGetRolesForALoggedInUser() {
        return Arrays.asList(new UserRolesDTO("admin", 1));
    }

    public static Map<String, List<String>> mockFailureTypesAndCauses() {
        Map<String, List<String>> failureTypesAndCauses = new HashMap<>();
        List<String> causesOne = new ArrayList<>();
        causesOne.add("Water in motor");
        causesOne.add("Dirt");
        causesOne.add("grease and foreign particles in motor");
        failureTypesAndCauses.put("Electrical", causesOne);
        return failureTypesAndCauses;
    }

    public static FailureType mockCreateAndUpdateFailureType() {
        FailureType failureType = new FailureType();
        failureType.setCauses("Dirt");
        failureType.setDescription("failureType");
        failureType.setFailuretypeid(10);
        failureType.setItemtypeid(20);
        return failureType;
    }

    public static List<WarrantyType> mockGetAllWarrantyTypeByCompanyId() {
        Company company = new Company();
        company.setCompanyid(453);
        return Arrays.asList(new WarrantyType(1, company, "Motor Only"));
    }

    public static Optional<WarrantyType> mockGetWarrantyType() {
        Company company = new Company();
        company.setCompanyid(453);
        return Optional.of(new WarrantyType(1, company, "Motor Only"));
    }

    public static WarrantyType mockCreateWarrantyType() {
        Company company = new Company();
        company.setCompanyid(453);
        return new WarrantyType(1, company, "Motor Only");
    }

    public static WarrantyType mockUpdateWarrantyType() {
        Company company = new Company();
        company.setCompanyid(453);
        return new WarrantyType(1, company, "repair");
    }

    public static List<ItemService> mockGetServices() {
        return Arrays.asList(new ItemService(1, 1, new Date(), "repair", true, new Date(), "ypatel", new Date(), "ypatel", new Date(), "ypatel"),
                new ItemService(2, 1, new Date(), "wire", false, new Date(), "ypatel", new Date(), "ypatel", new Date(), "ypatel"));
    }

    public static Optional<ItemService> mockGetItemService() {
        return Optional.of(new ItemService(1, 1, new Date(), "repair", true, new Date(), "ypatel", new Date(), "ypatel", new Date(), "ypatel"));
    }


    public static ItemServiceResource mockItemServiceResource() {
        return new ItemServiceResource(1, 1, new Date(), "repair", true, new Date(), "ypatel", new Date(), "ypatel", new Date(), "ypatel");
    }

    public static Map<String, List<ItemResource>> mockSearchItemsResponseForTablet() {
        Map<String, List<ItemResource>> items = new HashMap<>();
        items.put("Motor", mockListItemResource());
        return items;
    }


    public static Map<String, List<SearchResponseDTO>> mockSearchItemsResponse() {
        Map<String, List<SearchResponseDTO>> items = new HashMap<>();
        items.put("Motor", mockSearchResponseDTO());
        return items;
    }

    public static Map<String, Map<String, List<MasterSearchResponseDTO>>> mockMasterSearchServiceResponse() {
        Map<String, Map<String, List<MasterSearchResponseDTO>>> results = new HashMap<>();
        MasterSearchResponseDTO masterSearchResponseDTO = new MasterSearchResponseDTO();
        masterSearchResponseDTO.setItemId(389957);
        masterSearchResponseDTO.setCompanyId(279);
        masterSearchResponseDTO.setLocationId(366504);
        masterSearchResponseDTO.setTag("MM00008");
        List<MasterSearchResponseDTO> items = Arrays.asList(masterSearchResponseDTO);
        Map<String, List<MasterSearchResponseDTO>> typeItemMap = new HashMap<>();
        typeItemMap.put("Motor", items);
        results.put("ABC POWER COMPANY", typeItemMap);
        return results;
    }

    public static List<MasterSearchWithAttributesResponseDTO> mockMasterSearchWithAttributes() {
        MasterSearchWithAttributesResponseDTO masterSearchWithAttributesResponseDTO = new MasterSearchWithAttributesResponseDTO();
        masterSearchWithAttributesResponseDTO.setItemId(389957);
        masterSearchWithAttributesResponseDTO.setCompanyId(279);
        masterSearchWithAttributesResponseDTO.setStatus("In-Service");
        masterSearchWithAttributesResponseDTO.setTag("MM00008");
        masterSearchWithAttributesResponseDTO.setCompanyName("ABC POWER COMPANY");
        return Arrays.asList(masterSearchWithAttributesResponseDTO);
    }

    public static MasterSearchWithAttributesRequestDTO mockMasterSearchWithAttributesRequest() {
        MasterSearchWithAttributesRequestDTO masterSearchWithAttributesRequestDTO = new MasterSearchWithAttributesRequestDTO();
        masterSearchWithAttributesRequestDTO.setTag("MM00008");
        return masterSearchWithAttributesRequestDTO;
    }

    public static AdvanceSearchResource getAdvanceSearchRepairLogNoteByUser() {
        List<AdvanceSearchNotesDTO> itemNotes = new ArrayList<>();
        List<AdvanceSearchRepairDTO> repairLogList = new ArrayList<>();
        List<RFQResource> RFQsList = new ArrayList<>();
        return new AdvanceSearchResource(453,"123","10","20","30",true,true,true,"admin","1",itemNotes,repairLogList,RFQsList);
    }

    public static AdvanceSearchResource getAdvanceSearchRepairLogNoteByUserNotFound() {
        List<AdvanceSearchNotesDTO> itemNotes = new ArrayList<>();
        List<AdvanceSearchRepairDTO> repairLogList = new ArrayList<>();
        List<RFQResource> RFQsList = new ArrayList<>();
        return new AdvanceSearchResource(0,"123","10","20","30",true,true,true,"admin","1",itemNotes,repairLogList,RFQsList);
    }

    public static Map<String, List<ItemAdsSearchResource>> mockItemAdvancedSearch() {
        Map<String, List<ItemAdsSearchResource>> itemsOfAdvanceSearch = new HashMap<>();
        List<AttributeNameXml> attributeNameList = new ArrayList<>();
        List<ItemAdsSearchResource>  items = Arrays.asList(new ItemAdsSearchResource("Motor","123","Hyd","abc",1,1,"abc","InService",1,null,attributeNameList));
        itemsOfAdvanceSearch.put("Items",items);
        return itemsOfAdvanceSearch;
    }

    public static ItemAdsRequestDTO mockItemAdsRequestDTO() {
        List<AttributeName> attributeNameList = Arrays.asList(new AttributeName("1","hp","10"));
        return new ItemAdsRequestDTO(453,"motor","123","Hyd",1,1,1,1,true,"1",attributeNameList);
    }


    public static List<AdvanceSearchNotesDTO> mockGetAdvanceSearchItemNoteByUser() {
        return Arrays.asList(new AdvanceSearchNotesDTO("123",1,"motor",1,"abc",new Date(),"ypatel","10","20",1,1));
    }

    public static List<AdvanceSearchRepairDTO> mockGetAdvanceSearchRepairLogByUser() {
        return Arrays.asList(new AdvanceSearchRepairDTO(1,1,"motor","123","10","20","30","ben",1,1));
    }

    public static List<ItemAdsSearchResource> mockItemAdsSearchResource() {
        List<AttributeNameXml> attributeNameList = Arrays.asList(new AttributeNameXml("1","hp","10"));
        return Arrays.asList(new ItemAdsSearchResource("Motor","123","Hyd","abc",1,1,null,"InService",1,getAttributeXml(),attributeNameList));
    }

    public static FailureType mockCreateFailureType() {
        return new FailureType(1,1,"Not Working","Water In Motor");
    }

    public static List<FailureType> mockGetFailureTypeAndCause() {
        return Arrays.asList(new FailureType(1,1,"Not Working","Water In Motor"));
    }

    public static ItemServicesResource getServiceReports() {
        ItemServicesResource itemServicesResource = new ItemServicesResource();
        List<ServicesDTO> completedServices = Arrays.asList(new ServicesDTO(1, "123", "motor", new Date(), "water in motor", true, "ypatel", new Date()));
        List<ServicesDTO> inCompletedServices = Arrays.asList(new ServicesDTO(2, "1234", "motor", new Date(), "water in motor", false, "ypatel", new Date()));
        itemServicesResource.setCompletedServices(completedServices);
        itemServicesResource.setInCompletedServices(inCompletedServices);
        return itemServicesResource;
    }

    public static ServiceReportRequest mockServiceReportsRequestForMonth() {
        return new ServiceReportRequest(453, "Month", "20-06-2021", "20-07-2021");
    }

    public static ServiceReportRequest mockServiceReportsRequestForQuarter() {
        return new ServiceReportRequest(453, "Quarter", "20-06-2021", "20-07-2021");
    }

    public static ServiceReportRequest mockServiceReportsRequestForRange() {
        return new ServiceReportRequest(453, "Range", "20-06-2021", "20-07-2021");
    }

    public static VwSpareMotor getSpareMotor() {
        return new VwSpareMotor(1, "123", "motor", "1.5", "2.5", "10v", "2", "5", "3");
    }

    public static InserviceVsSpareResponseDTO getInServiceVsSpareReports() {
        InserviceVsSpareResponseDTO inserviceVsSpareResponseDTO = new InserviceVsSpareResponseDTO();
        List<SparesDTO> spares = new ArrayList<>();
        List<InServiceVsSpareDTO> inServiceAndSpareMotors = Arrays.asList(new InServiceVsSpareDTO(1, "motor", "123", "1.5", "2.0", "3.0", spares));
        List<InServiceMotorsDTO> unmatchedServiceMotors = Arrays.asList(new InServiceMotorsDTO(2, "234", "motor", "2.0", "3.0", "5.0"));
        List<InServiceMotorsDTO> unmatchedSpareMotors = Arrays.asList(new InServiceMotorsDTO(3, "345", "motor", "10", "20", "30"));
        inserviceVsSpareResponseDTO.setInServiceAndSpareMotors(inServiceAndSpareMotors);
        inserviceVsSpareResponseDTO.setUnmatchedServiceMotors(unmatchedServiceMotors);
        inserviceVsSpareResponseDTO.setUnmatchedSpareMotors(unmatchedSpareMotors);
        return inserviceVsSpareResponseDTO;
    }

    public static InServiceVsSpareRequest mockInServiceVsSpareRequset() {
        return new InServiceVsSpareRequest(453,"1.5","2.0","3.0");
    }

    public static Optional<VwSpareMotor> mockGetSpareMotor() {
        return Optional.of(new VwSpareMotor(1, "123", "motor", "1.5", "2.5", "10v", "2", "5", "3"));
    }

    public static List<ServicesDTO> mockGetServiceReports() {
        return Arrays.asList(new ServicesDTO(1, "123", "motor", new Date(), "water in motor", true, "ypatel", new Date()),
                new ServicesDTO(1, "123", "motor", new Date(), "water in motor", false, "ypatel", new Date()));
    }

    public static Map<String, Object> mockGetInServiceVsSpareReports() {
        Map<String, Object> reports = new HashMap<>();
        List<LinkedCaseInsensitiveMap<Object >> spares = new ArrayList<>();
        LinkedCaseInsensitiveMap<Object> item = new LinkedCaseInsensitiveMap<>();
        item.put("ItemID","1");
        item.put("TypeName","Motor");
        item.put("Tag","123");
        item.put("Hp","1.5");
        item.put("Rpm","1.2");
        item.put("Frame","1.1");
        spares.add(item);
        reports.put("#result-set-1",spares);
        reports.put("#result-set-2",spares);
        reports.put("#result-set-3",spares);
        return reports;
    }
}

