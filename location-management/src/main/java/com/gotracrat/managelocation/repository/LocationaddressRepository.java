/*
 * Created on 2018-07-15 ( Date ISO 2018-07-15 - Time 13:09:30 )
 * prabhakar
*/
package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.entity.Locationaddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Jpa repository for Locationaddress.
 * @author prabhakar
 */
@Repository
public interface LocationaddressRepository  extends JpaRepository<Locationaddress, Integer>, JpaSpecificationExecutor<Locationaddress> {}