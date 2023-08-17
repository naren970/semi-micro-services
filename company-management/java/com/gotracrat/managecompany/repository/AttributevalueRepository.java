package com.gotracrat.managecompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gotracrat.managecompany.entity.Attributevalue;
import com.gotracrat.managecompany.entity.AttributevalueKey;

/*
 * Jpa repository for Attributevalue.
 * @author prabhakar
 * since 2018-07-15
 */
@Repository
public interface AttributevalueRepository
		extends JpaRepository<Attributevalue, AttributevalueKey>, JpaSpecificationExecutor<Attributevalue> {

	public List<Attributevalue> findByCompositePrimaryKeyEntityidAndCompositePrimaryKeyEntitytypeid(Integer locationId,
			int entityTypeId);
}