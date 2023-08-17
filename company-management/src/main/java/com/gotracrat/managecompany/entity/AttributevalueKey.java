package com.gotracrat.managecompany.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

/**
 * Composite primary key for entity "Attributevalue" 
 * ( stored in table "AttributeValue" )
 * @author prabhakar
 * since 2018-05-25
 */

@Getter
@Setter
@Embeddable
public class AttributevalueKey implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AttributeNameID", nullable = false)
	@JsonBackReference
	private Attributename attributeName;
	@Column(name = "EntityID", nullable = false)
	private Integer entityid;
	@Column(name = "EntityTypeID", nullable = false)
	private Integer entitytypeid;

	public AttributevalueKey() {
		super();
	}

	public AttributevalueKey(Integer entityid, Integer entitytypeid) {
		super();
		this.entityid = entityid;
		this.entitytypeid = entitytypeid;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("|");
		sb.append(entityid);
		sb.append("|");
		sb.append(entitytypeid);
		return sb.toString();
	}
}
