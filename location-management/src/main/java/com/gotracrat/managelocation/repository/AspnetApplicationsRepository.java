/*
 * Created on 2018-10-27 ( Date ISO 2018-10-27 - Time 19:57:21 )
 * Generated by Prabhakar
 */
package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.entity.AspnetApplications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Jpa repository for AspnetApplications.
 *
 * @author Prabhakar
 */
@Repository
public interface AspnetApplicationsRepository extends JpaRepository<AspnetApplications, String>, JpaSpecificationExecutor<AspnetApplications> {
}