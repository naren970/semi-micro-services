package com.gotracrat.attributesandtypes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gotracrat.attributesandtypes.controller.resource.TypeDTO;
import com.gotracrat.attributesandtypes.entity.Type;

/**
 * Jpa repository for Type.
 * @author prabhakar
 * Since 2018-06-18
 */
@Repository
public interface TypeRepository  extends JpaRepository<Type, Integer>, JpaSpecificationExecutor<Type> {
	
	public List<Type> findByCompanyCompanyidAndEntitytypeid(int companyId, int entityTypeId);
	
	@Query(value="Select new com.gotracrat.attributesandtypes.controller.resource.TypeDTO(t.typeid,t.name,t.description,t.hostingfee) from Type t where t.company.companyid = ?1 and t.entitytypeid= ?2 ")
	public List<TypeDTO> getTypesByCompanyidAndEntitytypeid(Integer companyId, Integer entityTypeId);
}