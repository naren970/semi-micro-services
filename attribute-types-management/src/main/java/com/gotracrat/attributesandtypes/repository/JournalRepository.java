/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:58 )
 * 
*/
package com.gotracrat.attributesandtypes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gotracrat.attributesandtypes.entity.Attributename;
import com.gotracrat.attributesandtypes.entity.Journal;

/**
 * Jpa repository for Journal.
 * @author prabhakar
 */
@Repository
public interface JournalRepository  extends JpaRepository<Journal, Integer>, JpaSpecificationExecutor<Journal> {
	
	public List<Journal> findByJournaltypeidAndCompanyidAndEntitytypeidAndEntityidOrderByEffectiveonDesc(int journalTypeID, int companyID, int entitytypeid, int entityid);

	public List<Journal> findByCompanyidAndEntitytypeidAndEntityidAndJournaltypeidOrderByEffectiveonDesc(int companyID, int entitytypeid,
			int entityid,int journalTypeId);
	
}