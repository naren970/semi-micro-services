/*
 * Created on 2018-10-27 ( Date ISO 2018-10-27 - Time 19:57:22 )
 * Generated by 
*/
package com.gotracrat.managecompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gotracrat.managecompany.entity.AspnetMembership;

/**
 * Jpa repository for AspnetMembership.
 * @author Prabhakar
 */
@Repository
public interface AspnetMembershipRepository  extends JpaRepository<AspnetMembership, String>, JpaSpecificationExecutor<AspnetMembership> {}