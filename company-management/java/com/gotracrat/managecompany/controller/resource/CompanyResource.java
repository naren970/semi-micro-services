package com.gotracrat.managecompany.resource.resource;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/*
 * Hateoas resource associated with Company.
 * @author prabhakar
 * sinace 2018-05-25
 */
@Setter
@Getter
public class CompanyResource implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer companyid;
	private String userid;
	@NotEmpty(message = "Company name must not be empty")
	private String name;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String postalcode;
	private String phone;
	private String fax;
	private String url;
	private String description;
	private Integer statusid;
	private String lastmodifiedby;
	private String companyfilename;
	private String companycontenttype;
	private String companyimage;
	private Boolean vendor;
	private Boolean supplylevelwarning;
	private CompanyResource parentcompany;
	private Boolean issandbox;
	private int entityTypeId;
	private Integer typeId;
	private TypeResource type;
	private AnnouncementResource announcement;
	private StatusResource status;
	private List<AttributevalueResource> attributevalues;
	private List<CompanyResource> parentcompanyResourceList;
	private String filePath;
}