/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:55 )
 * 
*/
package com.gotracrat.managecompany.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Persistent class for Attributename entity stored in table AttributeName. This
 * class is a "record entity" without JPA links.
 * 
 * @author prabhakar
 */
@Entity
@Table(name = "AttributeName", schema = "dbo")
public class Attributename implements Serializable {

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AttributeNameID", nullable = false)
	private Integer attributenameid;
	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TypeID", nullable = false)
	@JsonBackReference
	private Type type;

	@Column(name = "Name", nullable = false, length = 100)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AttributeTypeID", nullable = false)
	@JsonBackReference
	private Attributetype attributetype;

	@Column(name = "Tooltip", length = 255)
	private String tooltip;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SearchTypeId", nullable = false)
	@JsonBackReference
	private Attributesearchtype searchtype;
	@Column(name = "SearchModifier")
	private String searchmodifier;
	@Column(name = "IsRequired", nullable = false)
	private Boolean isrequired;
	@Column(name = "DisplayOrder", nullable = false)
	private Integer displayorder;
	@Column(name = "IsRequiredForMatch")
	private Boolean isrequiredformatch;
	@Column(name = "IsManufacturer")
	private Boolean ismanufacturer;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "attributename")
	@JsonManagedReference
	private List<Attributelistitem> attributelistitem;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public Attributename() {
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------
	public void setAttributenameid(Integer attributenameid) {
		this.attributenameid = attributenameid;
	}

	public Integer getAttributenameid() {
		return this.attributenameid;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------
	// --- DATABASE MAPPING : TypeID (int)
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	// --- DATABASE MAPPING : Name (varchar)
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	// --- DATABASE MAPPING : AttributeTypeID (int)
	public Attributetype getAttributetype() {
		return attributetype;
	}

	public void setAttributetype(Attributetype attributetype) {
		this.attributetype = attributetype;
	}

	// --- DATABASE MAPPING : Tooltip (varchar)
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getTooltip() {
		return this.tooltip;
	}

	// --- DATABASE MAPPING : SearchTypeId (int)
	public Attributesearchtype getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(Attributesearchtype searchtype) {
		this.searchtype = searchtype;
	}

	// --- DATABASE MAPPING : SearchModifier (nvarchar)
	public void setSearchmodifier(String searchmodifier) {
		this.searchmodifier = searchmodifier;
	}

	public String getSearchmodifier() {
		return this.searchmodifier;
	}

	// --- DATABASE MAPPING : IsRequired (bit)
	public void setIsrequired(Boolean isrequired) {
		this.isrequired = isrequired;
	}

	public Boolean getIsrequired() {
		return this.isrequired;
	}

	// --- DATABASE MAPPING : DisplayOrder (int)
	public void setDisplayorder(Integer displayorder) {
		this.displayorder = displayorder;
	}

	public Integer getDisplayorder() {
		return this.displayorder;
	}

	// --- DATABASE MAPPING : IsRequiredForMatch (bit)
	public void setIsrequiredformatch(Boolean isrequiredformatch) {
		this.isrequiredformatch = isrequiredformatch;
	}

	public Boolean getIsrequiredformatch() {
		return this.isrequiredformatch;
	}

	// --- DATABASE MAPPING : IsManufacturer (bit)
	public void setIsmanufacturer(Boolean ismanufacturer) {
		this.ismanufacturer = ismanufacturer;
	}

	public Boolean getIsmanufacturer() {
		return this.ismanufacturer;
	}

	public List<Attributelistitem> getAttributelistitem() {
		return attributelistitem;
	}

	public void setAttributelistitem(List<Attributelistitem> attributelistitem) {
		this.attributelistitem = attributelistitem;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(attributenameid);
		sb.append(type);
		sb.append("|");
		sb.append(name);
		sb.append("|");
		// sb.append(attributetypeid);
		sb.append("|");
		sb.append(tooltip);
		sb.append("|");
		// sb.append(searchtypeid);
		sb.append("|");
		sb.append(searchmodifier);
		sb.append("|");
		sb.append(isrequired);
		sb.append("|");
		sb.append(displayorder);
		sb.append("|");
		sb.append(isrequiredformatch);
		sb.append("|");
		sb.append(ismanufacturer);
		return sb.toString();
	}
}