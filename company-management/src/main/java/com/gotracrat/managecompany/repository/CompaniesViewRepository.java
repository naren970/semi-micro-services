package com.gotracrat.managecompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gotracrat.managecompany.resource.CompanyDTO;
import com.gotracrat.managecompany.entity.CompaniesView;
/**
 * Jpa repository for CompaniesView.
 * Since:21-12-2020
 * @author:anudeep
 */

@Repository
public interface CompaniesViewRepository extends JpaRepository<CompaniesView, Integer>, 
                 JpaSpecificationExecutor<CompaniesView> {
	
	List<CompaniesView> findByOrderByName();
	
	@Query(value="select new com.gotracrat.managecompany.resource.CompanyDTO(c.companyid,c.name)"
			+ " from CompaniesView c ORDER BY name")
	List<CompanyDTO> getAllCompanies();
	
	String fetchCompanyByUserId = "SELECT * FROM vw_companies c WHERE c.CompanyId IN (SELECT v.CompanyId FROM vwUserSecurity v WHERE v.UserId =?1) order by c.Name";
	@Query(value=fetchCompanyByUserId, nativeQuery = true)
	List<CompaniesView> getCompaniesByUser(String userId);
}
