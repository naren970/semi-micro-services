/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:55 )
 * 
*/
package com.gotracrat.managecompany.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Persistent class for Attributesearchtype entity stored in table
 * AttributeSearchType. This class is a "record entity" without JPA links.
 * 
 * @author prabhakar
 */
@Entity
@Table(name = "AttributeSearchType", schema = "dbo")
public class Attributesearchtype implements Serializable {

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AttributeSearchTypeId", nullable = false)
	private Integer attributesearchtypeid;
	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@Column(name = "SearchType", nullable = false, length = 50)
	private String searchtype;
	
	@Column(name = "LookupCode", length = 5)
	private String lookupcode;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public Attributesearchtype() {
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------
	public void setAttributesearchtypeid(Integer attributesearchtypeid) {
		this.attributesearchtypeid = attributesearchtypeid;
	}

	public Integer getAttributesearchtypeid() {
		return this.attributesearchtypeid;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------
	// --- DATABASE MAPPING : SearchType (varchar)
	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}

	public String getSearchtype() {
		return this.searchtype;
	}

	// --- DATABASE MAPPING : LookupCode (varchar)
	public void setLookupcode(String lookupcode) {
		this.lookupcode = lookupcode;
	}

	public String getLookupcode() {
		return this.lookupcode;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(attributesearchtypeid);
		sb.append(searchtype);
		sb.append("|");
		sb.append(lookupcode);
		return sb.toString();
	}
}