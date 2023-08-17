/*
 * Created on 2018-07-15 ( Date ISO 2018-07-15 - Time 13:09:29 )
 * prabhakar
*/
package com.gotracrat.managecompany.resource;

import java.io.Serializable;

/**
 * Hateoas resource associated with Attributevalue.
 * 
 * @author prabhakar
 */
public class AttributevalueResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 487433988191644896L;
	private AttributenameResource attributename;
	private Integer entityid;
	private Integer entitytypeid;
	private String value;
	private String lastmodifiedby;

	// Constructor
	public AttributevalueResource() {
		// Needed empty constructor for serialization
	}

	public AttributenameResource getAttributename() {
		return attributename;
	}

	public void setAttributename(AttributenameResource attributename) {
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
}
