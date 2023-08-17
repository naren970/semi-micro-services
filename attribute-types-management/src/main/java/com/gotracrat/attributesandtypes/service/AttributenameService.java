/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:46:08 )
 *
*/
package com.gotracrat.attributesandtypes.service;

import java.util.List;
import java.util.Map;

import com.gotracrat.attributesandtypes.controller.resource.AttributeValueResource;
import com.gotracrat.attributesandtypes.controller.resource.AttributenameResource;
import com.gotracrat.attributesandtypes.entity.UserLog;

/**
 * Service interface for Attributename.
 * @author prabhakar
 */
public interface AttributenameService {

	/**
	 * Perform a pageable and filtered search.
	 * @param pageable pagination configuration
	 * @param criteria filters
	 * @return a page of attributename
	 */
	//public Page<Attributename> search(Pageable pageable, AttributenameCriteria criteria);
	/**
	 * Recover all attributename following an id.
	 * @param id the given typeId
	 * @return the List<attributename>
	 */
	public List<AttributenameResource> getAllAttributesByTypeId(Integer typeId);
	
	/**
	 * Recover an attributename following an id.
	 * @param id the given id
	 * @return the attributename
	 */
	public AttributenameResource getAttribute(Integer attributenameid);

	/**
	 * Perform an attributename deletion.
	 * @param id the given id
	 * @return state of deletion (true if ok otherwise false)
	 */
	public String delete(Integer attributenameid,UserLog userLog);

	/**
	 * Perform an attributename creation.
	 * @param attributename to create
	 * @return created attributename
	 */
	public AttributenameResource createAttribute(AttributenameResource attributename,UserLog userLog);

	/**
	 * Perform an attributename update.
	 * @param attributename to update
	 * @return state of update (true if ok otherwise false)
	 */
	public Boolean saveAttribute(AttributenameResource attributename,UserLog userLog);

	/**
	 * Test attributename existence.
	 * @param attributename to check
	 * @return true if author exist otherwise false
	 */
	public Boolean exist(AttributenameResource attributename);

	public List<AttributenameResource> updateAttributeOrder(List<AttributenameResource> attributenamelist);

	/**
	 * Recover Item types and attributes  by companyid.
	 * @param companyId the given companyId
	 * @return the map of type and corresponding attributes
	 */
	public Map<Integer, List<AttributenameResource>> getItemTypeAndAttributes(Integer companyId);

	public List<AttributeValueResource> getAllAttributesByEntityId(Integer entityId, Integer entityTypeId);
}
