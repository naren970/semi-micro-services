/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:46:09 )
 *
*/
package com.gotracrat.attributesandtypes.service;

import java.util.List;
import java.util.Optional;

import com.gotracrat.attributesandtypes.controller.resource.AttributetypeResource;
import com.gotracrat.attributesandtypes.entity.Attributetype;

/**
 * Service interface for Attributetype.
 * @author prabhakar
 */
public interface AttributetypeService {

	/**
	 * Perform a pageable and filtered search.
	 * @param pageable pagination configuration
	 * @param criteria filters
	 * @return a page of attributetype
	 */
	//public Page<Attributetype> search(Pageable pageable, AttributetypeCriteria criteria);
	/**
	 * Recover an attributetype following an id.
	 * @param id the given id
	 * @return the attributetype
	 */
	public List<AttributetypeResource> getAllAttributetypes();
	
	/**
	 * Recover an attributetype following an id.
	 * @param id the given id
	 * @return the attributetype
	 */
	public Optional<Attributetype> get(Integer attributetypeid);

	/**
	 * Perform an attributetype deletion.
	 * @param id the given id
	 * @return state of deletion (true if ok otherwise false)
	 */
	public Boolean delete(Integer attributetypeid);

	/**
	 * Perform an attributetype creation.
	 * @param attributetype to create
	 * @return created attributetype
	 */
	public Attributetype create(Attributetype attributetype);

	/**
	 * Perform an attributetype update.
	 * @param attributetype to update
	 * @return state of update (true if ok otherwise false)
	 */
	public Boolean save(Attributetype attributetype);

	/**
	 * Test attributetype existence.
	 * @param attributetype to check
	 * @return true if author exist otherwise false
	 */
	public Boolean exist(Attributetype attributetype);
}
