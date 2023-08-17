/*
 * Created on 2018-07-15 ( Date ISO 2018-07-15 - Time 13:09:30 )
 * prabhakar
*/
package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.dto.LocationMergeDTO;
import com.gotracrat.managelocation.entity.Location;
import com.gotracrat.managelocation.entity.UserLog;
import com.gotracrat.managelocation.resource.LocationResource;

import java.sql.SQLException;
import java.util.List;

//import com.gotracrat.managelocation.entity.Location;

/**
 * Service interface for Location.
 * 
 * @author prabhakar
 */
public interface LocationService {

	/**
	 * Perform a pageable and filtered search.
	 * 
	 * @param pageable
	 *            pagination configuration
	 * @param criteria
	 *            filters
	 * @return a page of location
	 */
	// public Page<Location> search(Pageable pageable, LocationCriteria criteria);
	/**
	 * Recover an location following an id.
	 * 
	 * @param id
	 *            the given id
	 * @return the location
	 */
	public LocationResource get(Integer locationid);

	/**
	 * Perform an location deletion.
	 * 
	 * @param id
	 *            the given id
	 * @return state of deletion (true if ok otherwise false)
	 */
	public String delete(Integer locationid, Integer companyId,UserLog userLog);

	/**
	 * Perform an location creation.
	 * 
	 * @param location
	 *            to create
	 * @return created location
	 */
	public List<LocationResource> createLocation(List<LocationResource> location);

	/**
	 * Perform an location update.
	 * 
	 * @param locationResource
	 *            to update
	 * @return state of update (true if ok otherwise false)
	 */
	public Boolean updateLocation(LocationResource locationResource);

	public List<Location> getAllLocations(int companyid);

	public List<LocationResource> getAllLocationsWithHierarchy(int companyid) throws SQLException;

	public LocationMergeDTO mergeLocation(LocationMergeDTO location, Integer companyId);

	 public List<LocationResource> getAllLocationsByUser(int companyid, String userid) throws SQLException;

	public LocationResource getCloneAddressFromParentLocation(Integer locationid, Integer companyid);

	Integer getParentLocationId(String name);
}
