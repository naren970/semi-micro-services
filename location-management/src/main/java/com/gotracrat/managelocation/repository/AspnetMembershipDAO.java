package com.gotracrat.managelocation.repository;

import java.util.List;

import com.gotracrat.managelocation.resource.AspnetMembershipResource;
import com.gotracrat.managelocation.resource.LogManagementResource;

public interface AspnetMembershipDAO {
	public Integer getCountOfUserIDByEmail( String emailid);

	public AspnetMembershipResource updateLoginDate(AspnetMembershipResource aspnetMembership);

	public List<LogManagementResource> getUsersLog(String companyid);

}
