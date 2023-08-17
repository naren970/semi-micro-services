package com.gotracrat.managelocation.repository;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.gotracrat.managelocation.resource.ProfileResource;

@Repository
public class  ProfileDAOImpl implements ProfileDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ProfileResource getProfileWithUserId(String userid) {
		 SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
					.withProcedureName("spProfileGetByUserID");
			SqlParameterSource in = new MapSqlParameterSource().addValue("UserID", userid);
			ProfileResource profile  = new ProfileResource();
			try {
			Map<String, Object> out = jdbcCall.execute(in);
			jdbcTemplate.getDataSource().getConnection().close();
			List profileList = (List) out.get("#result-set-1");
			Iterator i = profileList.iterator();
			
			while(i.hasNext())
			{
			    Map profileMap = (Map) i.next();
			    Integer companyid = (Integer) profileMap.get("CompanyID");
			    profile.setCompanyid(companyid);
		        Integer profileid = (Integer) profileMap.get("ProfileID");
				profile.setProfileid(profileid);
				String firstname = (String) profileMap.get("FirstName");
				profile.setFirstname(firstname);
				String lastname = (String) profileMap.get("LastName");
				profile.setLastname(lastname);
				String department = (String) profileMap.get("Department");
				profile.setDepartment(department);
				String jobtitle = (String) profileMap.get("JobTitle");
				profile.setJobtitle(jobtitle);
				String phone = (String) profileMap.get("Phone");
				profile.setPhone(phone);
				String mobilephone = (String) profileMap.get("MobilePhone");
				profile.setMobilephone(mobilephone);
				String fax = (String) profileMap.get("Fax");
				profile.setFax(fax);
				Boolean acceptedterms = (Boolean) profileMap.get("AcceptedTerms");
				profile.setAcceptedterms(acceptedterms);
				Boolean isowneradmin = (Boolean) profileMap.get("IsOwnerAdmin");
				profile.setIsowneradmin(isowneradmin);
				String username = (String) profileMap.get("UserName");
				profile.setUsername(username);
				String email = (String) profileMap.get("Email");
				profile.setEmail(email);
				Boolean hidepricing = (Boolean) profileMap.get("HidePricing");
				profile.setHidepricing(hidepricing);
				Boolean sendreceiverfq = (Boolean) profileMap.get("SendReceiveRFQ");
				profile.setSendreceiverfq(sendreceiverfq);
				Integer toplocationid = (Integer) profileMap.get("TopLocationId");
				profile.setToplocationid(toplocationid);
				Integer preferredlocationid = (Integer) profileMap.get("PreferredLocationId");
				profile.setPreferredlocationid(preferredlocationid);
				Boolean isOwnerAdminReadOnly = (Boolean) profileMap.get("IsOwnerAdminReadOnly");
				profile.setIsOwnerAdminReadOnly(isOwnerAdminReadOnly);
			}
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		return profile;
	}

	
		public Integer getUserTypeID(String companyId) {
		 SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
					.withProcedureName("spTypeGetUserTypeIdByCompanyId");
			SqlParameterSource in = new MapSqlParameterSource().addValue("CompanyId", companyId);
			int typeId = 0;
			try {
			Map<String, Object> out = jdbcCall.execute(in);
			jdbcTemplate.getDataSource().getConnection().close();
			List typeList = (List) out.get("#result-set-1");
			Iterator i = typeList.iterator();
			
		
			if(i.hasNext())
			{
			    Map typeMap = (Map) i.next();
			    typeId= (Integer) typeMap.get("TypeId");
	}
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			return typeId;
	}
	


}
