package com.gotracrat.managecompany.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Persistent class for Attributevalue entity stored in table AttributeValue.
 * This class is a "record entity" without JPA links.
 * @author prabhakar
 * since 2018-05-25 
 */
@Entity
@Table(name = "AttributeValue", schema = "dbo")
public class Attributevalue implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AttributevalueKey compositePrimaryKey;

	@Column(name = "Value")
	private String value;
	
	@Column(name = "LastModifiedBy", nullable = false)
	private String lastmodifiedby;

	public Attributevalue() {
		super();
		this.compositePrimaryKey = new AttributevalueKey();
	}
	
	public AttributevalueKey getCompositePrimaryKey() {
		return compositePrimaryKey;
	}

	public void setCompositePrimaryKey(AttributevalueKey compositePrimaryKey) {
		this.compositePrimaryKey = compositePrimaryKey;
	}

	public void setEntityid(Integer entityId) {
		this.compositePrimaryKey.setEntityid(entityId);
	}

	public Integer getEntityid() {
		return this.compositePrimaryKey.getEntityid();
	}

	public void setEntitytypeid(Integer entityTypeId) {
		this.compositePrimaryKey.setEntitytypeid(entityTypeId);
	}

	public Integer getEntitytypeid() {
		return this.compositePrimaryKey.getEntitytypeid();
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public void setLastmodifiedby(String lastmodifiedby) {
		this.lastmodifiedby = lastmodifiedby;
	}

	public String getLastmodifiedby() {
		return this.lastmodifiedby;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[" + compositePrimaryKey + "]");
		sb.append(value);
		sb.append("|");
		sb.append(lastmodifiedby);
		return sb.toString();
	}
}