/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:46:09 )
 *
 */
package com.gotracrat.attributesandtypes.service;

import java.util.List;
import java.util.Map;

import com.gotracrat.attributesandtypes.controller.resource.TypeDTO;
import com.gotracrat.attributesandtypes.controller.resource.TypeResource;
import com.gotracrat.attributesandtypes.entity.Type;
import com.gotracrat.attributesandtypes.entity.UserLog;

/**
 * Service interface for Type.
 *
 * @author prabhakar
 */
public interface TypeService {

	/**
	 * Perform a pageable and filtered search.
	 * 
	 * @param pageable pagination configuration
	 * @param criteria filters
	 * @return a page of type
	 */

	/**
	 * Recover an type following an id.
	 *
	 * @param id the given id
	 * @return the type
	 */
	public TypeResource get(Integer typeId);

	/**
	 * Perform an type deletion.
	 * 
	 * @param userName
	 *
	 * @param id       the given id
	 * @return state of deletion (true if ok otherwise false)
	 */
	public boolean delete(Type type, UserLog userLog);

	/**
	 * Perform an type creation.
	 *
	 * @param type to create
	 * @return created type
	 */
	public Type create(TypeResource type, UserLog userLog);

	/**
	 * Perform an type update.
	 *
	 * @param type    to update
	 * @param userLog
	 * @return state of update (true if ok otherwise false)
	 */
	public boolean save(TypeResource type, UserLog userLog);

	/**
	 * Test type existence.
	 *
	 * @param type to check
	 * @return true if author exist otherwise false
	 */
	public boolean exist(Type type);

	public List<TypeDTO> getAllTypes(Integer companyId, String moduleType);

	public List<TypeResource> getAllTypeWithHierarchy(Integer companyId, String moduleType);

	public Map<String, Integer> getAttributesForSearchDisplay(Integer companyId);

}
