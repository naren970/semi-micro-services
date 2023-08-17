package com.gotracrat.managecompany.repository;

import com.gotracrat.managecompany.resource.TemplateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Slf4j
@Repository
public class TemplateDAOImpl implements TemplateDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String buildCompanyAsXml(TemplateDto templateDto) {
        String xml="";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            CallableStatement cstmt = con.prepareCall("{call spCompanyExportAsXML(?, ? ,?)}");
            cstmt.setInt(1, templateDto.getCompanyId());
            cstmt.setBoolean(2, templateDto.getIncludeAllElements());
            cstmt.registerOutParameter(3, Types.SQLXML);
            cstmt.executeUpdate();
            xml= cstmt.getString(3);
        } catch (SQLException e) {
            log.error("Error while exporting company as Xml" + e.getMessage());
        }
        return xml;
    }
}
