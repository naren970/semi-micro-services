package com.gotracrat.managelocation.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gotracrat.managelocation.resource.LocationResource;


public class CloneAddressRowMapper implements  RowMapper<Object> {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

		
		 LocationResource locationResource = new LocationResource();
		 //locationResource.setLocationid(rs.getInt("LocationID"));
		 locationResource.setAddress1(rs.getString("address1"));
		 locationResource.setAddress2(rs.getString("address2"));
		 locationResource.setCity(rs.getString("city"));
		 locationResource.setState(rs.getString("state"));
		 locationResource.setPostalcode(rs.getString("postalcode"));
		
		return locationResource;
	}
}
