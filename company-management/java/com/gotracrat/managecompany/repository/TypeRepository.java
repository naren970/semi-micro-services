package com.gotracrat.managecompany.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.gotracrat.managecompany.entity.Type;


/**
 * Jpa repository for Type.
 * @author prabhakar
 * since 2018-06-18
 */
@Repository
public interface TypeRepository  extends JpaRepository<Type, Integer>, JpaSpecificationExecutor<Type> {
	
	public List<Type> findByCompanyCompanyidAndEntitytypeid(int companyId, int entitytypeid);
}