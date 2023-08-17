package com.gotracrat.managecompany.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gotracrat.managecompany.resource.resource.TemplateDto;

@Repository
public class TemplateDAOImpl implements TemplateDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String getXml(TemplateDto templateDto) throws SQLException {
		   Connection con = jdbcTemplate.getDataSource().getConnection();
		   CallableStatement cstmt = con.prepareCall("{call spCompanyExportAsXML(?, ? ,?)}");
		   cstmt.setInt(1,templateDto.getCompanyId());
		   cstmt.setBoolean(2, templateDto.getIncludeAllElements());
		   cstmt.registerOutParameter(3, Types.SQLXML);
		   cstmt.executeUpdate();
		   String xml=cstmt.getString(3);
		return xml;
	}
}
