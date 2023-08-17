package com.gotracrat.managecompany.repository;

import java.sql.SQLException;
import java.util.List;

import com.gotracrat.managecompany.resource.resource.TemplateDto;
import com.gotracrat.managecompany.resource.resource.TemplateWithOutXml;

public interface TemplateDAO {

	String getXml(TemplateDto templateDto) throws SQLException;
}
