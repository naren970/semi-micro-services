package com.gotracrat.managecompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gotracrat.managecompany.entity.Manual;

/**
 * Jpa repository for Manual.
 * @author shirisha
 */
@Repository
public interface ManualRepository  extends JpaRepository<Manual, Integer>, JpaSpecificationExecutor<Manual>  {

}
