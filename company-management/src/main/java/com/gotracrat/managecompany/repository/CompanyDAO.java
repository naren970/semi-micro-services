package com.gotracrat.managecompany.repository;

import com.gotracrat.managecompany.entity.CompaniesView;
import com.gotracrat.managecompany.resource.CompanyResource;

import java.util.List;

public interface CompanyDAO {
	public void saveEntityTypeType(CompanyResource company);
	public int getType(Integer companyid);
	public void deleteEntityTypeType(Integer companyid);
	public List<CompaniesView> getCompaniesByUser(String userid);
}