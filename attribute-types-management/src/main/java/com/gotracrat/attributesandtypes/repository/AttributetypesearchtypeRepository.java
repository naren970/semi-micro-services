/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:46:09 )
 *
*/
package com.gotracrat.attributesandtypes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gotracrat.attributesandtypes.entity.Attributesearchtype;
import com.gotracrat.attributesandtypes.entity.Attributetypesearchtype;
import com.gotracrat.attributesandtypes.entity.AttributetypesearchtypeKey;

/**
 * Jpa repository for Attributetypesearchtype.
 * @author prabhakar
 */
@Repository
public interface AttributetypesearchtypeRepository  extends JpaRepository<Attributetypesearchtype, AttributetypesearchtypeKey>, JpaSpecificationExecutor<Attributetypesearchtype> {
	
	public List<Attributetypesearchtype> findByCompositePrimaryKeyAttributetypeidAttributetypeid(int attributetypeid);
}