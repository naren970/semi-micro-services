/*
 * Created on 2018-07-15 ( Date ISO 2018-07-15 - Time 13:09:29 )
 * prabhakar
*/
package com.gotracrat.managelocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gotracrat.managelocation.entity.Attributevalue;
import com.gotracrat.managelocation.entity.AttributevalueKey;

/**
 * Jpa repository for Attributevalue.
 * @author prabhakar
 */
@Repository
public interface AttributevalueRepository  extends JpaRepository<Attributevalue, AttributevalueKey>, JpaSpecificationExecutor<Attributevalue> {
	
	public List<Attributevalue> findByCompositePrimaryKeyEntityidAndCompositePrimaryKeyEntitytypeid(Integer locationId, int entityTypeId);
}