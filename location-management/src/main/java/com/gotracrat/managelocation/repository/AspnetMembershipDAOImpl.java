package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.resource.AspnetMembershipResource;
import com.gotracrat.managelocation.resource.LogManagementResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class AspnetMembershipDAOImpl implements AspnetMembershipDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override       
	public Integer getCountOfUserIDByEmail( String emailid) {
		//companyid=279;
		//userid="72915A16-7DBC-431F-B2C4-208968F2B5DF";
		Integer userCount=0;
		//select COUNT(LOCATIONID) as locationcount from usersecurity where UserID='72915A16-7DBC-431F-B2C4-208968F2B5DF' and CompanyID=279 and LocationID=0
		String sql = "select COUNT(UserId) as userCount from aspnet_Membership where Email = ?";
	
			
		try { 
			userCount = jdbcTemplate.queryForObject(sql, new Object[] {emailid},Integer.class);
			jdbcTemplate.getDataSource().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userCount;
	}
	
	@Override       
	public AspnetMembershipResource  updateLoginDate( AspnetMembershipResource aspnetMembership) {
		//companyid=279;
		//userid="72915A16-7DBC-431F-B2C4-208968F2B5DF";
		Integer userCount=0;
		String Userid = aspnetMembership.getUserid();
		Date lastlogindate  =aspnetMembership.getLastlogindate();
		Date lastLoginDate = java.util.Calendar.getInstance().getTime();
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
				.withProcedureName("UpdateLoginDate");
				SqlParameterSource in = new MapSqlParameterSource().addValue("Userid", Userid)
				.addValue("lastlogindate", lastLoginDate);
				//List<ActiveItemsResource> activeItemsList = new ArrayList<>();
				try {
				Map<String, Object> out = jdbcCall.execute(in);
				jdbcTemplate.getDataSource().getConnection().close();
				}
				catch (SQLException e) {
			e.printStackTrace();
		}
		return aspnetMembership;
	}

	@Override
	public List<LogManagementResource> getUsersLog(String companyid) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
				.withProcedureName("spProfileGetByCompanyIdAsOwnerAdminForuserLog");
		SqlParameterSource in = new MapSqlParameterSource().addValue("CompanyID", companyid);
		List<LogManagementResource> usersList = new ArrayList<>();
		try {
			Map<String, Object> out = jdbcCall.execute(in);
			jdbcTemplate.getDataSource().getConnection().close();
			List profiles = (List) out.get("#result-set-1");

			Stream<Map> profilesStream = profiles.stream().map(Map.class::cast);
			usersList = profilesStream.map(rolesMap -> {
				LogManagementResource user = new LogManagementResource();
				user.setUserid((String) rolesMap.get("UserId"));
				user.setUsername((String) rolesMap.get("UserName"));
				user.setFirstname((String) rolesMap.get("FirstName"));
				user.setLastname((String) rolesMap.get("LastName"));
				user.setIsloggedin((Boolean) rolesMap.get("IsLoggedIn"));
				user.setLogincount((Integer) rolesMap.get("LoginCount"));
				user.setLastlogindate((Date) rolesMap.get("LastLoginDate"));
				user.setLastLogoutDate((Date) rolesMap.get("LastLogoutDate"));
				user.setIsOwnerAdmin((Boolean) rolesMap.get("IsOwnerAdmin"));
				return user;
			}).collect(Collectors.toList());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}
}
