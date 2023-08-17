package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.dto.LocationMergeDTO;
import com.gotracrat.managelocation.entity.Company;
import com.gotracrat.managelocation.entity.Location;
import com.gotracrat.managelocation.resource.LocationResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public class LocationDAOImpl implements LocationDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Location> getAllLocations(int companyid) {

		String sql = "select * from vw_locations where CompanyID='" + companyid + "'";
		
		return jdbcTemplate.query(sql, (rs, row) -> {
			Location location = new Location();
			location.setLocationid(rs.getInt("locationid"));
			location.setName(rs.getString("Name"));
			location.setState(rs.getString("State"));
			location.setDescription(rs.getString("Description"));
			jdbcTemplate.getDataSource().getConnection().close();
			return location;
		});
	}

	@Override
	public Location getLocation(Integer locationid) {
		
		Location location = null;
		String sql = "SELECT * FROM Location WHERE LocationID = ?";
		try {
			location = (Location) jdbcTemplate.queryForObject(sql, new Object[]{locationid}, (rs, row) -> {
				Location loc = new Location();
				Company company = new Company();
				loc.setLocationid(rs.getInt("locationid"));
				loc.setName(rs.getString("Name"));
				loc.setState(rs.getString("State"));
				loc.setDescription(rs.getString("Description"));
				company.setCompanyid(rs.getInt("CompanyID"));
				loc.setCompany(company);
				jdbcTemplate.getDataSource().getConnection().close();
				return loc;
			});
			jdbcTemplate.getDataSource().getConnection().close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return location;
	}

	@Override
	public LocationMergeDTO mergeLocations(LocationMergeDTO location) {
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
				  .withProcedureName("spLocationMergeSiblings"); 
		SqlParameterSource in = new
				  MapSqlParameterSource().addValue("OldLocationID", location.getOldLocationId()).addValue("NewLocationID", location.getNewLocationId()).addValue("NewLocationName", location.getNewLocationName());
		try {
		 jdbcCall.execute(in); 
		 jdbcTemplate.getDataSource().getConnection().close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
				 return location;
	
	}
	/*
	 * @Override public String getLocationName(Integer locationid) { SimpleJdbcCall
	 * jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
	 * .withProcedureName("spLocationGetPath"); SqlParameterSource in = new
	 * MapSqlParameterSource().addValue("LocationID", locationid); Map<String,
	 * Object> out = jdbcCall.execute(in); List locationNameList = (List)
	 * out.get("#result-set-1"); Iterator i = locationNameList.iterator(); Map
	 * locationNameMap = (Map) i.next(); String locationName = (String)
	 * locationNameMap.get("LocationPath"); return locationName;
	 * 
	 * }
	 */

	@Override
	public Boolean delete(Integer locationId) {

		 SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
					.withProcedureName("spLocationDelete");
			SqlParameterSource in = new MapSqlParameterSource().addValue("LocationID", locationId);
			try {
			Map<String, Object> out = jdbcCall.execute(in);
			 jdbcTemplate.getDataSource().getConnection().close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		
		return true;
	}

	@Override
	public void saveEntityTypeType(LocationResource LocationResource) throws SQLException {
		
			List<SqlParameter> params = new ArrayList<SqlParameter>();
			params.add(new SqlParameter(Types.INTEGER));
			params.add(new SqlParameter(Types.INTEGER));
			params.add(new SqlParameter(Types.INTEGER));
			
			
			jdbcTemplate.call(connection -> {

				CallableStatement callableStatement = connection
						.prepareCall("{call spEntityTypeTypeSave(?, ?, ?)}");
				callableStatement.setInt(1, LocationResource.getLocationid());

				callableStatement.setInt(2, LocationResource.getEntityTypeId());

				callableStatement.setInt(3, LocationResource.getTypeId());

				 jdbcTemplate.getDataSource().getConnection().close();

				return callableStatement;

			}, params);
		
		}


	@Override
	public LocationResource findBylocationid(Integer locationid) {
		 SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
					.withProcedureName("spLocationGetByLocationID");
			SqlParameterSource in = new MapSqlParameterSource().addValue("LocationID", locationid);
			LocationResource location  = new LocationResource();
			try {
			Map<String, Object> out = jdbcCall.execute(in);
			jdbcTemplate.getDataSource().getConnection().close();
			List LocationList = (List) out.get("#result-set-1");
			Iterator i = LocationList.iterator();
		
			while(i.hasNext())
			{
			    Map locationMap = (Map) i.next();
			    location.setLocationid((Integer) locationMap.get("LocationID"));
			    location.setCompanyID((Integer) locationMap.get("CompanyID"));
			    location.setParentID((Integer)locationMap.get("ParentID"));
			    location.setName((String)locationMap.get("Name"));	
			    location.setDescription((String)locationMap.get("Description"));
			    location.setStatusid((Integer)locationMap.get("StatusID"));
			    location.setLastmodifiedby((String)locationMap.get("LastModifiedBy"));
			    location.setDesiredspareratio((BigDecimal)locationMap.get("DesiredSpareRatio"));
			    location.setCriticalflag((boolean)locationMap.get("CriticalFlag"));
			    location.setIsvendor((boolean)locationMap.get("Isvendor"));
			    location.setVendorCompanyId((Integer)locationMap.get("VendorCompanyID"));
			    location.setAddress1((String)locationMap.get("Address1"));
			    location.setAddress2((String)locationMap.get("Address2"));
			    location.setCity((String)locationMap.get("City"));
			    location.setState((String)locationMap.get("State"));
			    location.setPostalcode((String)locationMap.get("PostalCode"));
			   // location.setTypeId((Integer)locationMap.get("TypeID"));
			
		
	          }
			
		    }
			catch(SQLException e) {
				e.printStackTrace();
			}
			return  location;
	}
	
	@Override
	public int getType(Integer locationId) {
		 SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
					.withProcedureName("spTypeGetByLocationID");
			SqlParameterSource in = new MapSqlParameterSource().addValue("LocationID", locationId);
			int typeId = 0;
			try {
			Map<String, Object> out = jdbcCall.execute(in);
			 jdbcTemplate.getDataSource().getConnection().close();
			
			List typeList = (List) out.get("#result-set-1");
			Iterator i = typeList.iterator();
			 typeId = 0;
			while(i.hasNext())
			{
			    Map typeMap = (Map) i.next();
			    typeId= (Integer) typeMap.get("TypeID");
	}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			return typeId;
	}

	@Override
	public LocationResource getCloneAddressFromParentLocation(Integer locationId, Integer companyId) {
		String sql = null;
		Integer idForQuery = null;
		LocationResource locationResource = null;

		if (locationId == 0) {
			sql = "SELECT * FROM COMPANY WHERE CompanyID=?";
			idForQuery = companyId;
		} else {
			sql = "SELECT * FROM LOCATION WHERE LocationID=?";
			idForQuery = locationId;
		}
		try {
			locationResource = (LocationResource) jdbcTemplate.queryForObject(
					sql, new Object[]{idForQuery},
					LocationDAOImpl::getLocationResource);
			jdbcTemplate.getDataSource().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return locationResource;
	}

	private static LocationResource getLocationResource(ResultSet rs, Integer row) throws SQLException {
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
