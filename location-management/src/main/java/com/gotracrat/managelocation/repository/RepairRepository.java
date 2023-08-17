package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Jpa repository for Repair.
 *
 * @author Telosys Prabhakar
 * @since 2018-11-30
 */
@Repository
public interface RepairRepository extends JpaRepository<Repair, Integer>, JpaSpecificationExecutor<Repair> {

    List<Repair> findByCompanyidAndTypeid(Integer companyId, Integer typeId);
}