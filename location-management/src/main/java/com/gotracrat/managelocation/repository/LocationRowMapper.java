package com.gotracrat.managelocation.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gotracrat.managelocation.entity.Location;

public class LocationRowMapper implements RowMapper<Object> { 

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

		
		 Location location = new Location();
		 location.setLocationid(rs.getInt("LocationID"));
		 //location.setCompanyid(rs.getInt("CompanyID"));
		
		return location;
	}

}
