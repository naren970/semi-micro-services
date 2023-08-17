/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:46:07 )
 *
*/
package com.gotracrat.attributesandtypes.service;

import java.util.Optional;

import com.gotracrat.attributesandtypes.entity.Attributelistitem;

/**
 * Service interface for Attributelistitem.
 * @author prabhakar
 */
public interface AttributelistitemService {

	/**
	 * Perform a pageable and filtered search.
	 * @param pageable pagination configuration
	 * @param criteria filters
	 * @return a page of attributelistitem
	 */
	//public Page<Attributelistitem> search(Pageable pageable, AttributelistitemCriteria criteria);
	/**
	 * Recover an attributelistitem following an id.
	 * @param id the given id
	 * @return the attributelistitem
	 */
	public Optional<Attributelistitem> get(Integer attributelistvaluesid);

	/**
	 * Perform an attributelistitem deletion.
	 * @param id the given id
	 * @return state of deletion (true if ok otherwise false)
	 */
	public Boolean delete(Integer attributelistvaluesid);

	/**
	 * Perform an attributelistitem creation.
	 * @param attributelistitem to create
	 * @return created attributelistitem
	 */
	public Attributelistitem create(Attributelistitem attributelistitem);

	/**
	 * Perform an attributelistitem update.
	 * @param attributelistitem to update
	 * @return state of update (true if ok otherwise false)
	 */
	public Boolean save(Attributelistitem attributelistitem);

	/**
	 * Test attributelistitem existence.
	 * @param attributelistitem to check
	 * @return true if author exist otherwise false
	 */
	public Boolean exist(Attributelistitem attributelistitem);
}
