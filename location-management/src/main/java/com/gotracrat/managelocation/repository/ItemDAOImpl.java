
package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.dto.*;
import com.gotracrat.managelocation.resource.AttributeName;
import com.gotracrat.managelocation.resource.ItemResource;
import com.gotracrat.managelocation.resource.FailedItemsResource;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.w3c.dom.CharacterData;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author prabhakar
 */
@Repository
public class ItemDAOImpl implements ItemDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PersistenceContext
    public EntityManager em;

    @Override
    public ItemResource getItem(Integer itemId) {
        String sql = "select *,status from vwItem vw  left outer join Status S on vw.statusid=s.StatusID WHERE itemID = ?";
        return (ItemResource) jdbcTemplate.queryForObject(sql, new Object[]{itemId}, new ItemRowMapper());
    }

    @Override
    public String getLocationName(Integer locationId) { //need to refactor in tranferLog also
        String locationName = null;
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
                .withProcedureName("spLocationGetPath");
        SqlParameterSource in = new MapSqlParameterSource().addValue("LocationID", locationId);
        try {
            Map<String, Object> out = jdbcCall.execute(in);
            List locationNameList = (List) out.get("#result-set-1");
            Iterator i = locationNameList.iterator();
            Map locationNameMap = (Map) i.next();
            locationName = (String) locationNameMap.get("LocationPath");
            jdbcTemplate.getDataSource().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locationName;

    }


    @Override
    public Map<String, Object> getAllLocationByHierarchy(Integer companyId) {
        List<SqlParameter> paramList = new ArrayList<>();
        paramList.add(new SqlParameter(Types.INTEGER));

        return jdbcTemplate.call(connection -> {

            CallableStatement callableStatement = connection
                    .prepareCall("{call spLocationGetHierarchyCompanyID(?)}");
            callableStatement.setInt(1, companyId);
            return callableStatement;
        }, paramList);

    }

    private List<AttributeName> xmlParse(String attributeXml) {
        List<AttributeName> attributeNameList = new ArrayList<>();
        if (attributeXml != null && !attributeXml.isEmpty()) {

            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                InputSource is = new InputSource();
                is.setCharacterStream(new StringReader(attributeXml));

                Document doc = db.parse(is);
                NodeList nodes = doc.getElementsByTagName("AttributeName");

                for (int i = 0; i < nodes.getLength(); i++) {
                    AttributeName attributeName = new AttributeName();
                    Element element = (Element) nodes.item(i);

                    NodeList name = element.getElementsByTagName("Name");
                    Element line = (Element) name.item(0);
                    attributeName.setName(getCharacterDataFromElement(line));

                    NodeList title = element.getElementsByTagName("AttributeNameID");
                    line = (Element) title.item(0);
                    attributeName.setAttributeNameID(getCharacterDataFromElement(line));

                    NodeList value = element.getElementsByTagName("Value");
                    line = (Element) value.item(0);
                    attributeName.setValue(getCharacterDataFromElement(line));
                    attributeNameList.add(attributeName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return attributeNameList;
        }
        return attributeNameList;

    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }

    @Override
    public List<ItemResource> searchItems(ItemResource itemResourceReq, Integer companyId, Boolean isOwnerAdmin,
                                          String userId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
                .withProcedureName("spItemSearchByUser");
        SqlParameterSource in = new MapSqlParameterSource().addValue("CompanyID", companyId)
                .addValue("TagNumber", itemResourceReq.getTag()).addValue("StatusID", itemResourceReq.getStatusid())
                .addValue("LocationID", itemResourceReq.getLocationid()).addValue("TypeID", itemResourceReq.getTypeId())
                .addValue("IsOwnerAdmin", isOwnerAdmin).addValue("UserID", userId);
        List<ItemResource> itemResourceList = new ArrayList<>();
        try {
            Map<String, Object> out = jdbcCall.execute(in);
            jdbcTemplate.getDataSource().getConnection().close();
            List itemResourceListTemp = (List) out.get("#result-set-1");

            Stream<Map> itemResourceStream = itemResourceListTemp.stream().map(Map.class::cast);
            itemResourceList = itemResourceStream.map(repairMap -> {
                ItemResource itemResource = new ItemResource();
                itemResource.setTag(repairMap.get("Tag") != null ? (String) repairMap.get("Tag") : null);
                itemResource.setItemid(repairMap.get("ItemID") != null ? (Integer) repairMap.get("ItemID") : 0);
                itemResource
                        .setLocationid(repairMap.get("LocationID") != null ? (Integer) repairMap.get("LocationID") : 0);
                itemResource.setCompanyid(companyId);
                itemResource.setName(repairMap.get("Name") != null ? (String) repairMap.get("Name") : null);
                itemResource.setStatusid(repairMap.get("StatusID") != null ? (Integer) repairMap.get("StatusID") : 0);
                itemResource.setWarrantytypeid(
                        repairMap.get("WarrantyTypeID") != null ? (Integer) repairMap.get("WarrantyTypeID") : 0);
                itemResource.setWarrantyexpiration(
                        repairMap.get("WarrantyExpiration") != null ? (Date) repairMap.get("WarrantyExpiration")
                                : null);
                itemResource.setSerialnumber(
                        repairMap.get("SerialNumber") != null ? (String) repairMap.get("SerialNumber") : null);
                itemResource.setModelnumber(
                        repairMap.get("ModelNumber") != null ? (String) repairMap.get("ModelNumber") : null);
                itemResource.setMeantimebetweenservice(repairMap.get("MeanTimeBetweenService") != null
                        ? (Integer) repairMap.get("MeanTimeBetweenService")
                        : 0);
                itemResource.setInserviceon(
                        repairMap.get("InServiceOn") != null ? (Date) repairMap.get("InServiceOn") : null);
                itemResource.setLastmodifiedby(
                        repairMap.get("LastModifiedBy") != null ? (String) repairMap.get("LastModifiedBy") : null);
                itemResource.setTypeId(repairMap.get("TypeID") != null ? (Integer) repairMap.get("TypeID") : 0);
                itemResource.setTypeName(repairMap.get("TypeName") != null ? (String) repairMap.get("TypeName") : null);
                itemResource.setLocationName(
                        repairMap.get("LocationName") != null ? (String) repairMap.get("LocationName") : null);
                itemResource.setLocationPath(
                        repairMap.get("LocationPath") != null ? (String) repairMap.get("LocationPath") : null);
                itemResource.setStatusname(repairMap.get("Status") != null ? (String) repairMap.get("Status") : null);
                itemResource.setItemRank(repairMap.get("rank") != null ? (Integer) repairMap.get("rank") : 0);
                String attributesXML = repairMap.get("AttributesXML") != null ? repairMap.get("AttributesXML") + ""
                        : null;

                itemResource.setAttributeNameList(this.xmlParse(attributesXML));
                return itemResource;
            }).collect(Collectors.toList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemResourceList;
    }


    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<SearchResponseDTO> searchItems(ItemSearchRequestDTO request) {

        return em.createNativeQuery(
                "EXEC spItemSearchInCompany @CompanyID=:companyId,@TagNumber=:tag,"
                        + "@StatusID=:statusId,@LocationID=:locationId,"
                        + "@TypeID=:typeId,@IsOwnerAdmin=:isOwnerAdmin,@UserID=:userId")
                .setParameter("companyId", request.getCompanyId())
                .setParameter("tag", request.getTag())
                .setParameter("statusId", request.getStatusId())
                .setParameter("locationId", request.getLocationId())
                .setParameter("typeId", request.getTypeId())
                .setParameter("isOwnerAdmin", request.getIsOwnerAdmin())
                .setParameter("userId", request.getUserId())
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(SearchResponseDTO.class)).getResultList();
    }
    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<FailedItemsResource> getFailedItemsTwiceInYear(Integer companyId, String startDate, String endDate) {
        return em.createNativeQuery(
                        "EXEC spGetFailedItemsTwiceInYear @CompanyID=:companyId,@StartDate=:startDate,"
                                + "@EndDate=:endDate")
                .setParameter("companyId",companyId)
                .setParameter("startDate", startDate)
                .setParameter("endDate",endDate)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(FailedItemsResource.class)).getResultList();
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<MasterSearchResponseDTO> masterSearch(MasterSearchRequestDTO masterSearchRequestDTO) {
        return em.createNativeQuery(
                "EXEC spMasterSearch  @TagNumber =:tagNumber,@Name=:name,"
                        + "@LocationName=:locationName,@TypeName =:typeName,"
                        + "@StatusName =:statusName")
                .setParameter("tagNumber", masterSearchRequestDTO.getTag())
                .setParameter("name", masterSearchRequestDTO.getName())
                .setParameter("locationName", masterSearchRequestDTO.getLocationName())
                .setParameter("typeName", masterSearchRequestDTO.getTypeName())
                .setParameter("statusName", masterSearchRequestDTO.getStatusName())
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(MasterSearchResponseDTO.class)).getResultList();
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<MasterSearchWithAttributesResponseDTO> masterSearchWithAttributes(MasterSearchWithAttributesRequestDTO masterSearchWithAttributesRequestDTO) {
        return em.createNativeQuery(
                "EXEC spMasterSearchDum @TagNumber =:tagNumber,@Attributes =:attributes,@LocationName =:locationName,@Page =:page,@Size =:size")
                .setParameter("tagNumber", masterSearchWithAttributesRequestDTO.getTag())
                .setParameter("attributes", masterSearchWithAttributesRequestDTO.getAttributesXml())
                .setParameter("locationName", masterSearchWithAttributesRequestDTO.getLocationName())
                .setParameter("page", masterSearchWithAttributesRequestDTO.getPage())
                .setParameter("size", masterSearchWithAttributesRequestDTO.getSize())
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(MasterSearchWithAttributesResponseDTO.class)).getResultList();
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<FindReplacementDTO> getAllItemAttributesForReplacement(Integer itemId) {
        return em.createNativeQuery(
                "EXEC spFindReplacement  @ItemID =:itemId")
                .setParameter("itemId", itemId)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(FindReplacementDTO.class)).getResultList();
    }
}