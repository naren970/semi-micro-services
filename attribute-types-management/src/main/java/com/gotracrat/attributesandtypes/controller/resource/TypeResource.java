/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:48:29 )
 * 
*/
package com.gotracrat.attributesandtypes.controller.resource;

import java.math.BigDecimal;
import java.util.List;

/**
 * Hateoas resource associated with Type.
 * 
 * @author prabhakar
 */
public class TypeResource {

	private Integer typeid;
	private String name;
	private String description;
	private Integer entitytypeid;
	private CompanyResource company;
	private TypeResource parentid;
	private Boolean ishidden;
	private String lastmodifiedby;
	private BigDecimal hostingfee;
	private Integer attributesearchdisplay;
	private Integer typemtbs;
	private BigDecimal typespareratio;
	private String moduleType;
	private List<TypeResource> typeList;

	// Constructor
	public TypeResource() {
		// Needed empty constructor for serialization
	}

	public Integer getTypeid() {
		return this.typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getEntitytypeid() {
		return this.entitytypeid;
	}

	public void setEntitytypeid(Integer entitytypeid) {
		this.entitytypeid = entitytypeid;
	}

	public CompanyResource getCompany() {
		return this.company;
	}

	public void setCompany(CompanyResource company) {
		this.company = company;
	}

	public TypeResource getParentid() {
		return parentid;
	}

	public void setParentid(TypeResource parentid) {
		this.parentid = parentid;
	}

	public Boolean getIshidden() {
		return this.ishidden;
	}

	public void setIshidden(Boolean ishidden) {
		this.ishidden = ishidden;
	}

	public String getLastmodifiedby() {
		return this.lastmodifiedby;
	}

	public void setLastmodifiedby(String lastmodifiedby) {
		this.lastmodifiedby = lastmodifiedby;
	}

	public BigDecimal getHostingfee() {
		return this.hostingfee;
	}

	public void setHostingfee(BigDecimal hostingfee) {
		this.hostingfee = hostingfee;
	}

	public Integer getAttributesearchdisplay() {
		return this.attributesearchdisplay;
	}

	public void setAttributesearchdisplay(Integer attributesearchdisplay) {
		this.attributesearchdisplay = attributesearchdisplay;
	}

	public Integer getTypemtbs() {
		return this.typemtbs;
	}

	public void setTypemtbs(Integer typemtbs) {
		this.typemtbs = typemtbs;
	}

	public BigDecimal getTypespareratio() {
		return this.typespareratio;
	}

	public void setTypespareratio(BigDecimal typespareratio) {
		this.typespareratio = typespareratio;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public List<TypeResource> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<TypeResource> typeList) {
		this.typeList = typeList;
	}
	
	
}
