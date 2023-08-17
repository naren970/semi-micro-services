package com.gotracrat.managecompany.service;

import java.util.List;

import com.gotracrat.managecompany.resource.TemplateDto;
import com.gotracrat.managecompany.resource.TemplateResource;
import com.gotracrat.managecompany.resource.TemplateWithOutXml;
import com.gotracrat.managecompany.entity.Company;
import com.gotracrat.managecompany.entity.UserLog;

public interface TemplateService {

	TemplateResource createTemplate(TemplateDto templateDto,UserLog userLog);

	String delete(Integer templateId,UserLog userLog);

	Company createCompanyFromTemplate(TemplateDto templateDto);

	List<TemplateWithOutXml> getAllTemplateWithOutXml(Integer companyId);



}
