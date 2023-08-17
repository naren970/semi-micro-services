package com.gotracrat.managecompany.resource.resource;

public class TemplateDto {

	private Integer templateId;
	private String companyName;
	private Integer companyId;
	private String templateName;
	private String userName;
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	private Boolean includeAllElements;
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public Boolean getIncludeAllElements() {
		return includeAllElements;
	}
	public void setIncludeAllElements(Boolean includeAllElements) {
		this.includeAllElements = includeAllElements;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
