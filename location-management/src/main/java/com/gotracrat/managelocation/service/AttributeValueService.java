/*
 * Created on 2018-07-15 ( Date ISO 2018-07-15 - Time 13:09:29 )
 * prabhakar
*/
package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.entity.Attributevalue;

/**
 * Service interface for Attributevalue.
 * @author prabhakar
 */
public interface AttributeValueService {

	/**
	 * Perform a pageable and filtered search.
	 * @param pageable pagination configuration
	 * @param criteria filters
	 * @return a page of attributevalue
	 */
	//public Page<Attributevalue> search(Pageable pageable, AttributevalueCriteria criteria);
	/**
	 * Recover an attributevalue following an id.
	 * @param id the given id
	 * @return the attributevalue
	 */
	public Attributevalue get(Integer attributenameid, Integer entityid, Integer entitytypeid);

	/**
	 * Perform an attributevalue deletion.
	 * @param id the given id
	 * @return state of deletion (true if ok otherwise false)
	 */
	public Boolean delete(Integer attributenameid, Integer entityid, Integer entitytypeid);

	/**
	 * Perform an attributevalue creation.
	 * @param attributevalue to create
	 * @return created attributevalue
	 */
	public Attributevalue create(Attributevalue attributevalue);

	/**
	 * Perform an attributevalue update.
	 * @param attributevalue to update
	 * @return state of update (true if ok otherwise false)
	 */
	public Boolean save(Attributevalue attributevalue);

	/**
	 * Test attributevalue existence.
	 * @param attributevalue to check
	 * @return true if author exist otherwise false
	 */
	public Boolean exist(Attributevalue attributevalue);
}
