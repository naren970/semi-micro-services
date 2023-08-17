/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:46:09 )
 *
*/
package com.gotracrat.attributesandtypes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gotracrat.attributesandtypes.controller.resource.AttributesearchtypeResource;
import com.gotracrat.attributesandtypes.entity.Attributesearchtype;
import com.gotracrat.attributesandtypes.entity.Attributetypesearchtype;

/**
 * Service interface for Attributetypesearchtype.
 * 
 * @author prabhakar
 */
public interface AttributetypesearchtypeService {

	/**
	 * Perform a pageable and filtered search.
	 * 
	 * @param pageable
	 *            pagination configuration
	 * @return a page of attributetypesearchtype
	 */
	public Page<Attributetypesearchtype> search(Pageable pageable);

	/**
	 * Recover an Attributesearchtype following an id.
	 * 
	 * @param id
	 *            the given id
	 * @return the Attributesearchtype
	 */
	public List<AttributesearchtypeResource> getAllAttributeSearchType(Integer attributetypeid);

	/**
	 * Recover an attributetypesearchtype following an id.
	 * 
	 * @param id
	 *            the given id
	 * @return the attributetypesearchtype
	 */
	public Optional<Attributetypesearchtype> get(Integer attributetypeid, Integer attributesearchtypeid);

	/**
	 * Perform an attributetypesearchtype deletion.
	 * 
	 * @param id
	 *            the given id
	 * @return state of deletion (true if ok otherwise false)
	 */
	public Boolean delete(Integer attributetypeid, Integer attributesearchtypeid);

	/**
	 * Perform an attributetypesearchtype creation.
	 * 
	 * @param attributetypesearchtype
	 *            to create
	 * @return created attributetypesearchtype
	 */
	public Attributetypesearchtype create(Attributetypesearchtype attributetypesearchtype);

	/**
	 * Perform an attributetypesearchtype update.
	 * 
	 * @param attributetypesearchtype
	 *            to update
	 * @return state of update (true if ok otherwise false)
	 */
	public Boolean save(Attributetypesearchtype attributetypesearchtype);

	/**
	 * Test attributetypesearchtype existence.
	 * 
	 * @param attributetypesearchtype
	 *            to check
	 * @return true if author exist otherwise false
	 */
	public Boolean exist(Attributetypesearchtype attributetypesearchtype);

}
