/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:48:29 )
 *
*/
package com.gotracrat.attributesandtypes.controller.resource;

/**
 * Hateoas resource associated with Attributetype.
 * @author prabhakar
 */
public class AttributetypeResource {

    private Integer attributetypeid;  
    private String typename;  
    private String description;  

	// Constructor
	public AttributetypeResource() {
		// Needed empty constructor for serialization
	}

	public Integer getAttributetypeid() {
		return this.attributetypeid;
	}
	public void setAttributetypeid(Integer attributetypeid) {
		this.attributetypeid = attributetypeid;
	}
	public String getTypename() {
		return this.typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
