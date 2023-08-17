/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:48:28 )
 *
*/
package com.gotracrat.attributesandtypes.controller.resource;

import java.util.List;

/**
 * Hateoas resource associated with Attributename.
 * 
 * @author prabhakar
 */
public class AttributenameResource {

	private Integer attributenameid;
	private TypeResource type;
	private String name;
	private AttributetypeResource attributetype;
	private String tooltip;
	private AttributesearchtypeResource searchtype;
	private String searchmodifier;
	private Boolean isrequired;
	private Integer displayorder;
	private Boolean isrequiredformatch;
	private Boolean ismanufacturer;
	private List<AttributelistitemResource>  attributelistitemResource;
	private Integer companyId;
	private String lastmodifiedby;
	private String moduleType;

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getLastmodifiedby() {
		return lastmodifiedby;
	}

	public void setLastmodifiedby(String lastmodifiedby) {
		this.lastmodifiedby = lastmodifiedby;
	}

	// Constructor
	public AttributenameResource() {
		// Needed empty constructor for serialization
	}

	public Integer getAttributenameid() {
		return this.attributenameid;
	}

	public void setAttributenameid(Integer attributenameid) {
		this.attributenameid = attributenameid;
	}

	public TypeResource getType() {
		return type;
	}

	public void setType(TypeResource type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTooltip() {
		return this.tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public AttributetypeResource getAttributetype() {
		return attributetype;
	}

	public void setAttributetype(AttributetypeResource attributetype) {
		this.attributetype = attributetype;
	}

	public AttributesearchtypeResource getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(AttributesearchtypeResource searchtype) {
		this.searchtype = searchtype;
	}

	public String getSearchmodifier() {
		return this.searchmodifier;
	}

	public void setSearchmodifier(String searchmodifier) {
		this.searchmodifier = searchmodifier;
	}

	public Boolean getIsrequired() {
		return this.isrequired;
	}

	public void setIsrequired(Boolean isrequired) {
		this.isrequired = isrequired;
	}

	public Integer getDisplayorder() {
		return this.displayorder;
	}

	public void setDisplayorder(Integer displayorder) {
		this.displayorder = displayorder;
	}

	public Boolean getIsrequiredformatch() {
		return this.isrequiredformatch;
	}

	public void setIsrequiredformatch(Boolean isrequiredformatch) {
		this.isrequiredformatch = isrequiredformatch;
	}

	public Boolean getIsmanufacturer() {
		return this.ismanufacturer;
	}

	public void setIsmanufacturer(Boolean ismanufacturer) {
		this.ismanufacturer = ismanufacturer;
	}

	public List<AttributelistitemResource> getAttributelistitemResource() {
		return attributelistitemResource;
	}

	public void setAttributelistitemResource(List<AttributelistitemResource> attributelistitemResource) {
		this.attributelistitemResource = attributelistitemResource;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	
	
}
