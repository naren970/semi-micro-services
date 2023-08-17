/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:58 )
 * 
*/
package com.gotracrat.managecompany.resource.resource;

/**
 * Hateoas resource associated with Entitytype-type.
 * @author prabhakar
 */
public class EntitytypeTypeResource  {

    private Integer entityid;  
    private Integer entitytypeid;  
    private Integer typeid;  

	// Constructor
	public EntitytypeTypeResource() {
		// Needed empty constructor for serialization
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
	public Integer getTypeid() {
		return this.typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
}
