/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:46:07 )
 *
*/
package com.gotracrat.managecompany.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Persistent class for Attributelistitem entity stored in table
 * AttributeListItem. This class is a "record entity" without JPA links.
 * 
 * @author prabhakar
 */
@Entity
@Table(name = "AttributeListItem", schema = "dbo")
public class Attributelistitem implements Serializable {

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AttributeListValuesID", nullable = false)
	private Integer attributelistvaluesid;
	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "AttributeNameID", nullable = false)
	private Attributename attributename;
	
	@Column(name = "ListItem", nullable = false, length = 50)
	private String listitem;
	@Column(name = "ListItemBK")
	private Integer listitembk;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public Attributelistitem() {
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------
	public void setAttributelistvaluesid(Integer attributelistvaluesid) {
		this.attributelistvaluesid = attributelistvaluesid;
	}

	public Integer getAttributelistvaluesid() {
		return this.attributelistvaluesid;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------
	// --- DATABASE MAPPING : AttributeNameID (int)
	public Attributename getAttributename() {
		return attributename;
	}

	public void setAttributename(Attributename attributename) {
		this.attributename = attributename;
	}

	// --- DATABASE MAPPING : ListItem (varchar)
	public void setListitem(String listitem) {
		this.listitem = listitem;
	}

	public String getListitem() {
		return this.listitem;
	}

	// --- DATABASE MAPPING : ListItemBK (int)
	public void setListitembk(Integer listitembk) {
		this.listitembk = listitembk;
	}

	public Integer getListitembk() {
		return this.listitembk;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(attributelistvaluesid);
		//sb.append(attributenameid);
		sb.append("|");
		sb.append(listitem);
		sb.append("|");
		sb.append(listitembk);
		return sb.toString();
	}
}