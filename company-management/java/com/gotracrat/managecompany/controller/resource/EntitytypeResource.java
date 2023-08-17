/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:57 )
 * 
*/
package com.gotracrat.managecompany.resource.resource;

/**
 * Hateoas resource associated with Entitytype.
 * @author prabhakar
 */
public class EntitytypeResource  {

    private Integer entitytypeid;  
    private String name;  
    private String description;  

	// Constructor
	public EntitytypeResource() {
		// Needed empty constructor for serialization
	}

	public Integer getEntitytypeid() {
		return this.entitytypeid;
	}
	public void setEntitytypeid(Integer entitytypeid) {
		this.entitytypeid = entitytypeid;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
