package com.gotracrat.attributesandtypes.repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class TypeDAOImpl implements TypeDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Boolean delete(Integer typeId) {
		
		
		 SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
					.withProcedureName("spTypeDelete");
			SqlParameterSource in = new MapSqlParameterSource().addValue("TypeID", typeId);
			try {
			Map<String, Object> out = jdbcCall.execute(in);
			jdbcTemplate.getDataSource().getConnection().close();
	}
			 catch (SQLException e) {
		       		// TODO Auto-generated catch block
		       		e.printStackTrace();
		       	}
		
		return true;
	}

	@Override
	public Map<String, Integer> getAttributesForSearchDisplay(Integer companyId) {
		
		 Map<String, Integer> attributesMap=new HashMap<>();
		String sql="select * from type where entitytypeid=2 and companyid=?";
		try {
		  List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, companyId);
		  jdbcTemplate.getDataSource().getConnection().close();
		  String name=null;
		  Integer attributeSearchDisplay=0;
		  for (Map row : rows) {
	          name=(String) row.get("Name");
	          attributeSearchDisplay= (Integer)row.get("AttributeSearchDisplay");
	          attributesMap.put(name, attributeSearchDisplay);
	           
	        }
	}
		  catch (SQLException e) {
	       		// TODO Auto-generated catch block
	       		e.printStackTrace();
	       	}
		
		return attributesMap;
	}
	
	
	
}
