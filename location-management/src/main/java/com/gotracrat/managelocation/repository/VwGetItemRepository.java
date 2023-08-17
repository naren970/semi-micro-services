package com.gotracrat.managelocation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gotracrat.managelocation.entity.VwGetItem;
import com.gotracrat.managelocation.entity.Vwitem;

@Repository
public interface VwGetItemRepository extends JpaRepository<VwGetItem, Integer>, JpaSpecificationExecutor<Vwitem> {

}
