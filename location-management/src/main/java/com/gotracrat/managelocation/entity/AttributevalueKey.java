/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:56 )
 * 
*/
package com.gotracrat.managelocation.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Composite primary key for entity "Attributevalue" ( stored in table
 * "AttributeValue" )
 * 
 * @author prabhakar
 */
@Embeddable
public class AttributevalueKey implements Serializable {
	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY KEY ATTRIBUTES
	// ----------------------------------------------------------------------
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AttributeNameID", nullable = false)
	@JsonBackReference
	private Attributename attributename;

	@Column(name = "EntityID", nullable = false)
	private Integer entityid;
	@Column(name = "EntityTypeID", nullable = false)
	private Integer entitytypeid;

	// ----------------------------------------------------------------------
	// CONSTRUCTORS
	// ----------------------------------------------------------------------
	public AttributevalueKey() {
		super();
	}

	public AttributevalueKey(Integer attributenameid, Integer entityid, Integer entitytypeid) {
		super();
		this.entityid = entityid;
		this.entitytypeid = entitytypeid;
	}

	public AttributevalueKey(Attributename attributename, Integer entityid, Integer entitytypeid) {
		super();
		this.attributename = attributename;
		this.entityid = entityid;
		this.entitytypeid = entitytypeid;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR KEY FIELDS
	// ----------------------------------------------------------------------
	public Attributename getAttributename() {
		return attributename;
	}

	public void setAttributename(Attributename attributename) {
		this.attributename = attributename;
	}

	public void setEntityid(Integer value) {
		this.entityid = value;
	}

	public Integer getEntityid() {
		return this.entityid;
	}

	public void setEntitytypeid(Integer value) {
		this.entitytypeid = value;
	}

	public Integer getEntitytypeid() {
		return this.entitytypeid;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("|");
		sb.append(entityid);
		sb.append("|");
		sb.append(entitytypeid);
		return sb.toString();
	}
}
