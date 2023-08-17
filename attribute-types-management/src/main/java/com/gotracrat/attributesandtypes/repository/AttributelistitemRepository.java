/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:46:07 )
 *
*/
package com.gotracrat.attributesandtypes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gotracrat.attributesandtypes.entity.Attributelistitem;

/**
 * Jpa repository for Attributelistitem.
 * @author prabhakar
 */
@Repository
public interface AttributelistitemRepository  extends JpaRepository<Attributelistitem, Integer>, JpaSpecificationExecutor<Attributelistitem> {
	
	public void deleteAllByAttributenameAttributenameid(int attributenameid);
}