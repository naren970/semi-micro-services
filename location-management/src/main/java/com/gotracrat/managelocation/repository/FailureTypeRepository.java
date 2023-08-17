/*
 * Created on 2018-11-29 ( Date ISO 2018-11-29 - Time 19:27:58 )
 * Generated by Prabhakar
 */
package com.gotracrat.managelocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gotracrat.managelocation.entity.FailureType;

/**
 * Jpa repository for Failuretype.
 *
 * @author Prabhakar
 */
@Repository
public interface FailureTypeRepository
        extends JpaRepository<FailureType, Integer>, JpaSpecificationExecutor<FailureType> {

    public List<FailureType> findByItemtypeid(Integer itemtypeid);

    @Query(value = "SELECT FAILURETYPEID FROM FAILURETYPE  WHERE   ITEMTYPEID =:itemtypeid AND DESCRIPTION=:description", nativeQuery = true)
    Integer findByitemtypeidAndDescription(@Param("itemtypeid") Integer itemtypeid, @Param("description") String description);


}