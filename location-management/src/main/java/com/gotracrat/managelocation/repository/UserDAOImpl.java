package com.gotracrat.managelocation.repository;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@Override
	public Integer getCountOfUserIDByUserName( String userName) {
		//companyid=279;
		//userid="72915A16-7DBC-431F-B2C4-208968F2B5DF";
		Integer userCount=0;
		//select COUNT(LOCATIONID) as locationcount from usersecurity where UserID='72915A16-7DBC-431F-B2C4-208968F2B5DF' and CompanyID=279 and LocationID=0
		String sql = "select COUNT(UserId) as usercount from aspnet_Users where UserName = ?";
	
		
		try { 
			userCount = jdbcTemplate.queryForObject(sql, new Object[] {userName},Integer.class);
			jdbcTemplate.getDataSource().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userCount;
	}

}
