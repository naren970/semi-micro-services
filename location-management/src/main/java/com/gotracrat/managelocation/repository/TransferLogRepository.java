package com.gotracrat.managelocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gotracrat.managelocation.entity.TransferLog;
@Repository
public interface TransferLogRepository  extends JpaRepository<TransferLog, Integer>, JpaSpecificationExecutor<TransferLog> {

	List<TransferLog> findByItemIDOrderByTransferDateDesc(@Param("itemId") Integer itemId);
}


