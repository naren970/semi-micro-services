/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:58 )
 * 
*/
package com.gotracrat.attributesandtypes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gotracrat.attributesandtypes.entity.Journaltype;

/**
 * Jpa repository for Journaltype.
 * @author prabhakar
 */
@Repository
public interface JournaltypeRepository  extends JpaRepository<Journaltype, Integer>, JpaSpecificationExecutor<Journaltype> {}