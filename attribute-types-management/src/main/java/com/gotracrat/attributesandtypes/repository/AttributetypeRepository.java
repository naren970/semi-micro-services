/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:46:08 )
 *
*/
package com.gotracrat.attributesandtypes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gotracrat.attributesandtypes.entity.Attributetype;

/**
 * Jpa repository for Attributetype.
 * @author prabhakar
 */
@Repository
public interface AttributetypeRepository  extends JpaRepository<Attributetype, Integer>, JpaSpecificationExecutor<Attributetype> {}