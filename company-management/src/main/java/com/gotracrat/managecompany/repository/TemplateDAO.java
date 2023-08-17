package com.gotracrat.managecompany.repository;

import com.gotracrat.managecompany.resource.TemplateDto;

public interface TemplateDAO {

	String buildCompanyAsXml(TemplateDto templateDto);
}
