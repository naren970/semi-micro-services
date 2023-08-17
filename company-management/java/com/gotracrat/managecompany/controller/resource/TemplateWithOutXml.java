package com.gotracrat.managecompany.resource.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemplateWithOutXml {
	    private Integer templateID;
	    private String name;
	    private Integer companyID;
		public TemplateWithOutXml(Integer templateID, String name, Integer companyID) {
			super();
			this.templateID = templateID;
			this.name = name;
			this.companyID = companyID;
		}
}
		