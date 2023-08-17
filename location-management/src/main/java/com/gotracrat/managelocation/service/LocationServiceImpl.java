/*
 * Created on 2018-07-15 ( Date ISO 2018-07-15 - Time 13:09:30 )
 * prabhakar
 */
package com.gotracrat.managelocation.service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.gotracrat.managelocation.dto.LocationMergeDTO;
import com.gotracrat.managelocation.dto.LocationNamesDTO;
import com.gotracrat.managelocation.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.gotracrat.managelocation.resource.AttributeNameResource;
import com.gotracrat.managelocation.resource.AttributeTypeResource;
import com.gotracrat.managelocation.resource.AttributeValueResource;
import com.gotracrat.managelocation.resource.CompanyResource;
import com.gotracrat.managelocation.resource.LocationResource;
import com.gotracrat.managelocation.resource.TypeResource;
import com.gotracrat.managelocation.entity.Attributename;
import com.gotracrat.managelocation.entity.Attributetype;
import com.gotracrat.managelocation.entity.Attributevalue;
import com.gotracrat.managelocation.entity.AttributevalueKey;
import com.gotracrat.managelocation.entity.Company;
import com.gotracrat.managelocation.entity.Location;
import com.gotracrat.managelocation.entity.Type;
import com.gotracrat.managelocation.entity.UserLog;
import com.gotracrat.managelocation.exception.ObjectNotFoundException;
import com.gotracrat.managelocation.repository.AttributevalueRepository;
import com.gotracrat.managelocation.repository.ItemDAO;
import com.gotracrat.managelocation.repository.LocationDAO;
import com.gotracrat.managelocation.repository.LocationRepository;
import com.gotracrat.managelocation.repository.UserLogRepository;
import com.gotracrat.managelocation.repository.UserSecurityDAO;
import com.gotracrat.managelocation.utils.EntityTypeIDEnum;
import com.gotracrat.managelocation.utils.GoTracratConstants;
import com.gotracrat.managelocation.utils.GotracratUtility;
import com.gotracrat.managelocation.utils.ModulesEnum;

/**
 * Service implementation for Location Call.
 *
 * @author prabhakar
 */
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserLogRepository userLogRepository;

    @Autowired
    private UserSecurityDAO userSecurityDAO;

    @Autowired
    private LocationDAO locationDAO;

    @Autowired
    private AttributevalueRepository attributevalueRepository;

    @Autowired
    private ItemDAO itemDAOImpl;

    @Value("${cache.enabled}")
    private boolean cacheEnabled;

    @Autowired
    private LocationCacheService locationCacheService;

    @Override
    public LocationResource get(Integer locationid) {
        LocationResource location = locationDAO.findBylocationid(locationid);
        if (location != null) {
            int typeId = locationDAO.getType(locationid);
            location.setTypeId(typeId);
            List<Attributevalue> attributevalueList = attributevalueRepository
                    .findByCompositePrimaryKeyEntityidAndCompositePrimaryKeyEntitytypeid(location.getLocationid(), 3);
            if (attributevalueList != null && !attributevalueList.isEmpty()) {
                List<AttributeValueResource> attributevalueResourceList = this
                        .buildAttributevalueResources(attributevalueList);
                location.setAttributevalues(attributevalueResourceList);
            }
            return location;
        }
        throw new ResourceNotFoundException(GoTracratConstants.INVALID_LOCATION_ID);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public String delete(Integer locationid, Integer companyId, UserLog userLog) {
        Location location = locationDAO.getLocation(locationid);
        if (location != null) {
            String actionComment = ModulesEnum.LOCATION.getModule() + " " + location.getName() + " ".concat(GoTracratConstants.DELETED_BY) + " ".concat(userLog.getUserName());
            userLog.setActionComment(actionComment);
            Boolean locationDeleted = locationDAO.delete(locationid);
            locationCacheService.deleteAllLocationsByHeirarchy(companyId);
            return GoTracratConstants.LOCATION_DELETED;
        }
        userLog.setCanNotInsert(true);
        throw new ResourceNotFoundException(GoTracratConstants.INVALID_LOCATION_ID);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<LocationResource> createLocation(List<LocationResource> locationResources) {

        int count = 0;

        for (LocationResource locationResource : locationResources) {
            if (StringUtils.isEmpty(locationResource.getName()) || locationResource.getTypeId() == 0) {
                count++;
            }
        }

        if (count > 0) {
            throw new ObjectNotFoundException("LocationName and Type  are required while adding a location");
        }


        List<UserLog> userLogList = new ArrayList<>();
        List<Location> locations = new ArrayList<>();
        locationResources.forEach(locationResource -> {
            Location location = new Location();
            this.buildLocation(locationResource, location);
            String actionComment = ModulesEnum.LOCATION.getModule() + " " + location.getName() + " ".concat(GoTracratConstants.CREATED_BY) + " ".concat(location.getLastmodifiedby());
            UserLog toBeSavedUserLog = GotracratUtility.getUserLog(location.getLastmodifiedby(), location.getCompany().getCompanyid(), ModulesEnum.LOCATION.getModule(), actionComment);
            userLogList.add(toBeSavedUserLog);
            Location locationTemp = locationRepository.save(location);
            locationCacheService.updateAllLocationsByHeirarchy(location.getCompany().getCompanyid());
            if (locationTemp != null && locationTemp.getLocationid() != null) {
                locationResource.setLocationid(locationTemp.getLocationid());
                locationResource.setEntityTypeId(EntityTypeIDEnum.LOCATION_ENTITYTYPE_ID.entityTypeID());
                try {
                    locationDAO.saveEntityTypeType(locationResource);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            List<AttributeValueResource> attributevalueResources = locationResource.getAttributevalues();

            if (locationTemp.getLocationid() != null && locationTemp.getLocationid() != 0
                    && attributevalueResources != null && !attributevalueResources.isEmpty()) {
                List<Attributevalue> attributevalues = buildAttributevalues(attributevalueResources, locationTemp);

                if (!attributevalues.isEmpty()) {
                    attributevalueRepository.saveAll(attributevalues);
                }
            }
            locations.add(locationTemp);
        });
        userLogRepository.saveAll(userLogList);
        return buildLocationResourceList(locations);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Boolean updateLocation(LocationResource locationResource) {
        if (StringUtils.isEmpty(locationResource.getName()) || locationResource.getTypeId() == 0) {
            throw new ObjectNotFoundException("LocationName and LocationType  are required while updating a location");
        }
        final Integer pk = locationResource.getLocationid();
        Location location = locationRepository.findBylocationid(pk);
        if (location != null) {
            Location location1 = buildLocation(locationResource, location);
            if (locationResource.getParentLocation().getLocationid() == -1)
                location1.setParentlocation(null);
            locationRepository.save(location1);
            if (location1.getLocationid() != null) {
                locationResource.setLocationid(location1.getLocationid());
                locationResource.setEntityTypeId(EntityTypeIDEnum.LOCATION_ENTITYTYPE_ID.entityTypeID());
                try {
                    locationDAO.saveEntityTypeType(locationResource);
                    locationCacheService.updateAllLocationsByHeirarchy(locationResource.getCompany().getCompanyid());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            List<AttributeValueResource> attributevalueResources = locationResource.getAttributevalues();
            if (attributevalueResources != null && !attributevalueResources.isEmpty()) {
                List<Attributevalue> attributevalues = buildAttributevalues(attributevalueResources, location);
                if (!attributevalues.isEmpty()) {
                    attributevalueRepository.saveAll(attributevalues);
                }
            }
            return true;
        }
        throw new ResourceNotFoundException(GoTracratConstants.INVALID_LOCATION_ID);
    }

    @Override
    public List<Location> getAllLocations(int companyid) {
        List<Location> locationList = locationDAO.getAllLocations(companyid);
        if (locationList == null || !locationList.isEmpty()) {
            return locationList;
        }
        throw new ResourceNotFoundException(GoTracratConstants.INVALID_COMPANY_ID);
    }

    @Override
    public List<LocationResource> getAllLocationsWithHierarchy(int companyid) throws SQLException {
        Map<String, Object> resultMap;
        if (cacheEnabled) {
            resultMap = locationCacheService.getAllLocationsByHeirarchy(companyid);
        } else {
            resultMap = itemDAOImpl.getAllLocationByHierarchy(companyid);
        }
        List<LocationResource> locationResourcesList = new ArrayList<>();
        Map<Integer, List<LocationResource>> childList = new LinkedHashMap<>();
        if (resultMap != null && !resultMap.isEmpty()) {
            List<Map<Integer, List<LocationResource>>> itemList = (List) resultMap.get("#result-set-1");
            Iterator<Map<Integer, List<LocationResource>>> i = itemList.iterator();
            while (i.hasNext()) {
                LocationResource locationResource = new LocationResource();
                Map locationMap = (Map) i.next();
                String name = locationMap.get("Name") != null ? locationMap.get("Name") + "" : null;
                locationResource.setName(name);
                LocationResource parentlocation = null;
                if (locationMap.get("ParentID") != null) {
                    Integer ParentID = (Integer) locationMap.get("ParentID");
                    parentlocation = new LocationResource();
                    parentlocation.setLocationid(ParentID);
                }
                locationResource.setParentLocation(parentlocation);
                Integer LocationID = (Integer) locationMap.get("LocationID");
                locationResource.setLocationid(LocationID);
                if (locationMap.get("ParentID") == null) {
                    locationResourcesList.add(locationResource);
                } else {
                    if (childList.containsKey((Integer) locationMap.get("ParentID"))) {
                        childList.get((Integer) locationMap.get("ParentID")).add(locationResource);
                    } else {
                        List<LocationResource> locationsList = new ArrayList<>();
                        locationsList.add(locationResource);
                        childList.put((Integer) locationMap.get("ParentID"), locationsList);
                    }
                }
            }
            childList.forEach((parentId, locationResourceList) ->
                locationResourceList.forEach(locationResource -> {
                    List<LocationResource> locationResourcesTempList = childList.get(locationResource.getLocationid());
                    if (locationResourcesTempList != null)
                        locationResourcesTempList.sort(Comparator.comparing(LocationResource::getName));
                    locationResource.setParentLocationResourceList(locationResourcesTempList);
                }));
            locationResourcesList.forEach(locationResource -> {
                List<LocationResource> locationResourcesTempList = childList.get(locationResource.getLocationid());
                if (locationResourcesTempList != null)
                    locationResourcesTempList.sort(Comparator.comparing(LocationResource::getName));
                locationResource.setParentLocationResourceList(locationResourcesTempList);
            });
            locationResourcesList.sort(Comparator.comparing(LocationResource::getName));
            return locationResourcesList;
        }
        throw new ResourceNotFoundException(GoTracratConstants.INVALID_COMPANY_ID);
    }

    private Location buildLocation(LocationResource locationResource, Location location) {
        location.setLocationid(locationResource.getLocationid());
        location.setName(locationResource.getName());
        location.setDescription(locationResource.getDescription());
        location.setStatusid(locationResource.getStatusid());
        location.setLastmodifiedby(locationResource.getLastmodifiedby());
        location.setDesiredspareratio(locationResource.getDesiredspareratio());
        location.setCriticalflag(locationResource.getCriticalflag());
        location.setIsvendor(locationResource.getIsvendor());
        location.setAddress1(locationResource.getAddress1());
        location.setAddress2(locationResource.getAddress2());
        location.setCity(locationResource.getCity());
        location.setState(locationResource.getState());
        location.setPostalcode(locationResource.getPostalcode());

        Company company = new Company();
        company.setCompanyid(locationResource.getCompany().getCompanyid());
        location.setCompany(company);

        int vendorId = locationResource.getVendorCompany().getCompanyid();
        Company vendorCompany = new Company();
        vendorCompany.setCompanyid(vendorId);
        location.setVendorCompany(vendorCompany);


        int parentlocationId = locationResource.getParentLocation() != null ?
                locationResource.getParentLocation().getLocationid() : 0;
        if (parentlocationId != 0 && parentlocationId != -1) {
            LocationResource locationResource1 = locationDAO.findBylocationid(parentlocationId);
            Location parentlocation = new Location();
            BeanUtils.copyProperties(locationResource1, parentlocation);
            parentlocation.setLocationid(parentlocationId);
            location.setParentlocation(parentlocation);
        }


        return location;
    }

    private List<Attributevalue> buildAttributevalues(List<AttributeValueResource> attributevalueResources,
                                                      Location location) {
        List<Attributevalue> attributevalues = new ArrayList<>();
        attributevalueResources.forEach(attributevalueresource -> {
            Attributevalue attributevalue = new Attributevalue();
            BeanUtils.copyProperties(attributevalueresource, attributevalue);
            attributevalue.setEntityid(location.getLocationid());
            attributevalue.setEntitytypeid(EntityTypeIDEnum.LOCATION_ENTITYTYPE_ID.entityTypeID());

            AttributevalueKey compositePrimaryKey = new AttributevalueKey();
            Attributename attributename = new Attributename();
            attributename.setAttributenameid(attributevalueresource.getAttributename().getAttributenameid());
            compositePrimaryKey.setAttributename(attributename);
            compositePrimaryKey.setEntityid(location.getLocationid());
            compositePrimaryKey.setEntitytypeid(EntityTypeIDEnum.LOCATION_ENTITYTYPE_ID.entityTypeID());
            attributevalue.setCompositePrimaryKey(compositePrimaryKey);

            attributevalues.add(attributevalue);
        });
        return attributevalues;
    }

    private List<LocationResource> buildLocationResourceList(List<Location> locations) {
        List<LocationResource> locationResourceList = new ArrayList<>();
        locations.forEach(location -> {
            LocationResource locationResource = new LocationResource();
            BeanUtils.copyProperties(location, locationResource);
            buildLocationResource(location, locationResource);
            locationResourceList.add(locationResource);
        });
        return locationResourceList;
    }

    private LocationResource buildLocationResource(Location location, LocationResource locationResource) {
        BeanUtils.copyProperties(location, locationResource);

        CompanyResource company = new CompanyResource();
        BeanUtils.copyProperties(location.getCompany(), company);
        locationResource.setCompany(company);

        if (location.getIsvendor()) {
            CompanyResource vendorCompany = new CompanyResource();
            if (location.getVendorCompany() != null) {
                BeanUtils.copyProperties(location.getVendorCompany(), vendorCompany);
                locationResource.setVendorCompany(vendorCompany);
            }

        }

        if (location.getParentlocation() != null) {
            LocationResource parentlocation = new LocationResource();
            BeanUtils.copyProperties(location.getParentlocation(), parentlocation);
            locationResource.setParentLocation(parentlocation);
        }

        return locationResource;
    }

    private List<AttributeValueResource> buildAttributevalueResources(List<Attributevalue> attributevalues) {

        List<AttributeValueResource> attributevalueResourceList = new ArrayList<>();
        attributevalues.forEach(attributevalue -> {

            AttributeValueResource attributevalueResource = new AttributeValueResource();
            BeanUtils.copyProperties(attributevalue, attributevalueResource);
            attributevalueResource.setEntityid(attributevalue.getEntityid());
            attributevalueResource.setEntitytypeid(attributevalue.getEntitytypeid());

            AttributeNameResource attributenameResource = new AttributeNameResource();
            BeanUtils.copyProperties(attributevalue.getCompositePrimaryKey().getAttributename(), attributenameResource);
            attributenameResource.setAttributenameid(
                    attributevalue.getCompositePrimaryKey().getAttributename().getAttributenameid());

            Attributetype attributetype = attributevalue.getCompositePrimaryKey().getAttributename().getAttributetype();
            AttributeTypeResource attributetypeResource = new AttributeTypeResource();
            BeanUtils.copyProperties(attributetype, attributetypeResource);
            attributenameResource.setAttributetype(attributetypeResource);

            Type type = attributevalue.getCompositePrimaryKey().getAttributename().getType();

            TypeResource typeResource = new TypeResource();
            BeanUtils.copyProperties(type, typeResource);
            attributenameResource.setType(typeResource);

            attributevalueResource.setAttributename(attributenameResource);
            attributevalueResourceList.add(attributevalueResource);
        });
        return attributevalueResourceList;
    }

    @Override
    public LocationMergeDTO mergeLocation(LocationMergeDTO location, Integer companyId) {
        LocationMergeDTO locationMergeDTO = locationDAO.mergeLocations(location);
        locationCacheService.updateAllLocationsByHeirarchy(companyId);
        return locationMergeDTO;
    }

    @Override
    public List<LocationResource> getAllLocationsByUser(int companyid, String userid) throws SQLException {

        List<LocationResource> locationWithHierarchyList = null;
        Integer count = userSecurityDAO.getCountOfLocationIDByUserId(companyid, userid);

        if (count >= 1) {
            return this.getAllLocationsWithHierarchy(companyid);
        } else {

            List<LocationNamesDTO> userLocationsList = userSecurityDAO.getLocationNames(userid, companyid);
            locationWithHierarchyList = this.getAllLocationsWithHierarchy(companyid);
            locationWithHierarchyList = locationWithHierarchyList.stream().filter(loc1 ->
                    userLocationsList.stream().map(LocationNamesDTO::getLocationid).collect(Collectors.toSet())
                            .contains(loc1.getLocationid())).collect(Collectors.toList());
        }


        return locationWithHierarchyList;
    }

    @Override
    public LocationResource getCloneAddressFromParentLocation(Integer locationid, Integer companyid) {
        return locationDAO.getCloneAddressFromParentLocation(locationid, companyid);
    }

    @Override
    public Integer getParentLocationId(String name) {
        return locationRepository.findLocationIdByName(name);

    }
}
