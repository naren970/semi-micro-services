package com.gotracrat.attributesandtypes.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.gotracrat.attributesandtypes.controller.resource.AttributenameResource;

@Repository
public class AttributeDAOImpl implements AttributeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<AttributenameResource> updateAttributeOrder(List<AttributenameResource> attributenamelist) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
				.withProcedureName("spAttributeNameSaveDisplayOrder");
		StringBuffer attributelist = new StringBuffer();
		Iterator<AttributenameResource> iterator = attributenamelist.iterator();
		while (iterator.hasNext()) {
			attributelist.append(iterator.next().getAttributenameid().toString() + "|");
		}
		String attributeorder = attributelist.toString();
		SqlParameterSource in = new MapSqlParameterSource().addValue("AttributeOrder", attributeorder);
		try {
		Map<String, Object> out = jdbcCall.execute(in);
		jdbcTemplate.getDataSource().getConnection().close();
		}
		 catch (SQLException e) {
	       		// TODO Auto-generated catch block
	       		e.printStackTrace();
	       	}
		return attributenamelist;

	}

	@Override
	public void delete(Integer attributenameid) {

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
				.withProcedureName("spAttributeNameDelete");
		SqlParameterSource in = new MapSqlParameterSource().addValue("AttributeNameID", attributenameid);
		try {
		Map<String, Object> out = jdbcCall.execute(in);
		jdbcTemplate.getDataSource().getConnection().close();
	}
	 catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

	}
	@Override
	public List<Integer> getTypeIdsByCompanyId(Integer companyId) {
		String sql="SELECT TYPEID FROM TYPE WHERE COMPANYID="+companyId+" AND ENTITYTYPEID=2";
		
		List<Integer> data =jdbcTemplate.query(sql, new RowMapper<Integer>(){
            public Integer mapRow(ResultSet rs, int rowNum) 
                                         throws SQLException {
                    return rs.getInt(1);
            }
       });

		
		return data;
		
	}


}
