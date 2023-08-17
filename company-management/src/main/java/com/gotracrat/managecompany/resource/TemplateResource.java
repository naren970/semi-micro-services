package com.gotracrat.managecompany.resource;


/**
 * Hateoas resource associated with Template.
 * @author manikanta
 */

public class TemplateResource {

	
	    private Integer templateID;
	    private String name;
	    private String Xml;
	    private Integer companyID;
		public Integer getTemplateID() {
			return templateID;
		}
		public void setTemplateID(Integer templateID) {
			this.templateID = templateID;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getXml() {
			return Xml;
		}
		public void setXml(String xml) {
			Xml = xml;
		}
		public Integer getCompanyID() {
			return companyID;
		}
		public void setCompanyID(Integer companyID) {
			this.companyID = companyID;
		}
	    
	    
}
