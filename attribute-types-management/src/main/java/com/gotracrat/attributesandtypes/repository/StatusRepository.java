/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:59 )
 * 
*/
package com.gotracrat.attributesandtypes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gotracrat.attributesandtypes.entity.Status;

/**
 * Jpa repository for Status.
 * 
 * @author prabhakar
 */
@Repository
public interface StatusRepository extends JpaRepository<Status, Integer>, JpaSpecificationExecutor<Status> {

	public List<Status> findByCompanyCompanyidAndEntitytypeid(int companyId,int entitytypeId);

	public List<Status>  findByCompanyCompanyidAndEntitytypeidAndStatusIn(int companyId,int entitytypeId,List<String> statusList);

}