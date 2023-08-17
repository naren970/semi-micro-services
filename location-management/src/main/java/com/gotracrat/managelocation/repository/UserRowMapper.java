package com.gotracrat.managelocation.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gotracrat.managelocation.entity.AspnetUsers;

public class UserRowMapper implements RowMapper<Object> { 

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

		
		AspnetUsers user = new AspnetUsers();
		 user.setUserid(rs.getString("UserId"));
		 user.setUsername(rs.getString("UserName"));

		return user;
	}

}
