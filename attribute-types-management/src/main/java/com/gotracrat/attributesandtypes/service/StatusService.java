/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:59 )
 * 
*/
package com.gotracrat.attributesandtypes.service;

import java.util.List;
import java.util.Optional;

import com.gotracrat.attributesandtypes.controller.resource.StatusResource;
import  com.gotracrat.attributesandtypes.entity.Status;
import com.gotracrat.attributesandtypes.entity.UserLog;

/**
 * Service interface for Status.
 * @author prabhakar
 */
public interface StatusService {

	/**
	 * Perform a pageable and filtered search.
	 * @param pageable pagination configuration
	 * @param criteria filters
	 * @return a page of status
	 */
	//public Page<Status> search(Pageable pageable, StatusCriteria criteria);
	/**
	 * Recover an status following an id.
	 * @param moduleType 
	 * @param id the given id
	 * @return the status
	 */
	public List<StatusResource> getAllStatusByCompanyId(int statusId, String moduleType);
	/**
	 * Recover an status following an id.
	 * @param id the given id
	 * @return the status
	 */
	public Optional<Status> get(Integer statusId);

	/**
	 * Perform an status deletion.
	 * @param id the given id
	 * @return state of deletion (true if ok otherwise false)
	 */
	public boolean delete(Status status,UserLog userLog);

	/**
	 * Perform an status creation.
	 * @param status to create
	 * @return created status
	 */
	public StatusResource createStatus(StatusResource statusResource,UserLog userLog);

	/**
	 * Perform an status update.
	 * @param status to update
	 * @return state of update (true if ok otherwise false)
	 */
	public boolean updateStatus(StatusResource statusResource,UserLog userLog);

	/**
	 * Test status existence.
	 * @param status to check
	 * @return true if author exist otherwise false
	 */
	public boolean exist(Status status);
	
	public List<StatusResource> getAllItemStatusTablet(Integer companyID);
}
