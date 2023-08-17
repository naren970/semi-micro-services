package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository  extends JpaRepository<Status, Integer>, JpaSpecificationExecutor<Status> {

    @Query(value="Select s.status from Status s where s.statusid =:statusId", nativeQuery = true)
    String findStatusByStatusid(@Param("statusId") Integer statusId);
}
