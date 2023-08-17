package com.gotracrat.managecompany.service;

import java.sql.SQLException;
import java.util.List;

import com.gotracrat.managecompany.resource.resource.CompanyExport;
import com.gotracrat.managecompany.resource.resource.TemplateDto;
import com.gotracrat.managecompany.resource.resource.TemplateResource;
import com.gotracrat.managecompany.resource.resource.TemplateWithOutXml;
import com.gotracrat.managecompany.entity.Company;
import com.gotracrat.managecompany.entity.Template;
import com.gotracrat.managecompany.entity.UserLog;

public interface TemplateService {

	List<TemplateResource> getAllTemplateByCompanyId(Integer companyid);

	TemplateResource createTemplate(TemplateDto templateDto,UserLog userLog) throws SQLException;

	String delete(Integer templateId,UserLog userLog);

	Company createCompanyFromTemplate(TemplateDto templateDto);

	List<TemplateWithOutXml> getAllTemplateWithOutXml(Integer companyId);



}
