package com.gotracrat.attributesandtypes.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.gotracrat.attributesandtypes.controller.resource.AttachmentResource;
import com.gotracrat.attributesandtypes.controller.resource.ItemChangeLog;

@Repository
public class JournalDAOImpl implements JournalDAO {

	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Integer getLocationId(Integer entityid) {
		
		Integer locationId=0;
		String sql = "SELECT LOCATIONID FROM ITEM WHERE ITEMID=?";
	
		
		try { 
			locationId = jdbcTemplate.queryForObject(sql, new Object[] {entityid},Integer.class);
			jdbcTemplate.getDataSource().getConnection().close();
		} 
		 catch (SQLException e) {
	       		e.printStackTrace();
	       	}
		return locationId;
	}

	@Override
	public AttachmentResource getEntityNameAndCompanyId(AttachmentResource attachmentResource) {
		Integer itemId=attachmentResource.getEntityid();
		jdbcTemplate.query("SELECT COMPANYID,TAG,LocationID FROM VWITEM WHERE ITEMID="+itemId+"", new ResultSetExtractor<Map>(){
		    @Override
		    public Map extractData(ResultSet rs) throws SQLException,DataAccessException {
		        HashMap<String,String> mapRet= new HashMap<String,String>();
		        while(rs.next()){
		        	
		        	attachmentResource.setCompanyID(rs.getInt("COMPANYID"));
		        	attachmentResource.setItemTag(rs.getString("TAG"));
		        	attachmentResource.setLocationId(rs.getInt("LocationID"));
		        	jdbcTemplate.getDataSource().getConnection().close();
		        }
		        return mapRet;
		    }
		});
		 return attachmentResource;
	}

	@Override
	public ItemChangeLog getLocationNames(Integer locationId, Integer previous_LocationId) {
		 SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
					.withProcedureName("spLocationGetPathForChangeLog");
			SqlParameterSource in = new MapSqlParameterSource().addValue("LocationID", locationId).addValue("PreviousLocationID", previous_LocationId);
			ItemChangeLog itemChangeLog  = new ItemChangeLog();
			try {
			Map<String, Object> out = jdbcCall.execute(in);
			jdbcTemplate.getDataSource().getConnection().close();
			List LocationList = (List) out.get("#result-set-1");

			Stream<Map> itemChangeLogMapStream = LocationList.stream().map(Map.class::cast);
				itemChangeLogMapStream.map(itemChangeLogMap -> {
					itemChangeLog.setLocation_Name((String)itemChangeLogMap.get("LocationPath"));
					itemChangeLog.setPrevious_LocationName((String)itemChangeLogMap.get("PreviousLocationPath"));
				return itemChangeLog;
				});
		    }
			catch(SQLException e) {
				e.printStackTrace();
			}
			return  itemChangeLog;
	}

	@Override
	public ItemChangeLog getStatusNames(Integer statusId, Integer previous_StatusId) {
		
		 SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
					.withProcedureName("spGetStatusNameForChangeLog");
			SqlParameterSource in = new MapSqlParameterSource().addValue("StatusID", statusId).addValue("PreviousStatusID", previous_StatusId);
			ItemChangeLog itemChangeLog  = new ItemChangeLog();
			try {
			Map<String, Object> out = jdbcCall.execute(in);
			jdbcTemplate.getDataSource().getConnection().close();
			List LocationList = (List) out.get("#result-set-1");

				Stream<Map> itemChangeLogMapStream = LocationList.stream().map(Map.class::cast);
				itemChangeLogMapStream.map(itemChangeLogMap -> {
					itemChangeLog.setStatus_Name((String)itemChangeLogMap.get("status"));
					itemChangeLog.setPreviousStatus_Name((String)itemChangeLogMap.get("PreviousStatus"));
					return itemChangeLog;
				});
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		return  itemChangeLog;
	}
}
