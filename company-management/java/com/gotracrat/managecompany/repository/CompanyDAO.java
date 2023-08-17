package com.gotracrat.managecompany.repository;

import java.sql.SQLException;

import com.gotracrat.managecompany.resource.resource.CompanyResource;

public interface CompanyDAO {
	public void saveEntityTypeType(CompanyResource company) throws SQLException;
	public int getType(Integer companyid);
	public void deleteEntityTypeType(Integer companyid);
}