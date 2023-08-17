/*
 * Created on 2018-07-15 ( Date ISO 2018-07-15 - Time 13:09:29 )
 * prabhakar
*/
package com.gotracrat.managelocation.resource;

import java.io.Serializable;

/**
 * Hateoas resource associated with Attributevalue.
 * 
 * @author prabhakar
 */
public class AttributeValueResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 487433988191644896L;
	private AttributeNameResource attributename;
	private Integer entityid;
	private Integer entitytypeid;
	private String value;
	private String lastmodifiedby;

	// Constructor
	public AttributeValueResource() {
		// Needed empty constructor for serialization
	}

	public AttributeNameResource getAttributename() {
		return attributename;
	}

	public void setAttributename(AttributeNameResource attributename) {
		this.attributename = attributename;
	}

	public Integer getEntityid() {
		return this.entityid;
	}

	public void setEntityid(Integer entityid) {
		this.entityid = entityid;
	}

	public Integer getEntitytypeid() {
		return this.entitytypeid;
	}

	public void setEntitytypeid(Integer entitytypeid) {
		this.entitytypeid = entitytypeid;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLastmodifiedby() {
		return this.lastmodifiedby;
	}

	public void setLastmodifiedby(String lastmodifiedby) {
		this.lastmodifiedby = lastmodifiedby;
	}

	public AttributeValueResource(AttributeNameResource attributename, Integer entityid, Integer entitytypeid,
								  String value, String lastmodifiedby) {
		super();
		this.attributename = attributename;
		this.entityid = entityid;
		this.entitytypeid = entitytypeid;
		this.value = value;
		this.lastmodifiedby = lastmodifiedby;
	}
	
}
