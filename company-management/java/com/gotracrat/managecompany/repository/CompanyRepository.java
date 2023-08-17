package com.gotracrat.managecompany.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.gotracrat.managecompany.entity.Company;

import feign.Param;

/**
 * Jpa repository for Company.
 * @author prabhakar 
 * since 2018-05-25
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>, JpaSpecificationExecutor<Company> {

	public List<Company> findAllByVendor(boolean vendorFlag);

	public List<Company> findAllByVendorAndParentcompanyCompanyid(boolean vendorFlag, Integer companyid);

	@Transactional
	@Procedure(procedureName = "spCompanyDelete")
	void deleteCompany(@Param("CompanyID") Integer companyId);
}