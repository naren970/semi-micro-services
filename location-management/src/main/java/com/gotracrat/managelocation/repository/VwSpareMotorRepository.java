package com.gotracrat.managelocation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gotracrat.managelocation.entity.VwSpareMotor;

/**
 * Jpa repository for VwSpareMotor View.
 * 
 * @author Manikanta
 */
@Repository
public interface VwSpareMotorRepository
		extends JpaRepository<VwSpareMotor, Integer>, JpaSpecificationExecutor<VwSpareMotor> {

}
