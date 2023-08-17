/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:48:29 )
 *
*/
package com.gotracrat.attributesandtypes.controller.resource;

/**
 * Hateoas resource associated with Attributetypesearchtype.
 * @author prabhakar
 */
public class AttributetypesearchtypeResource  {

    private Integer attributetypeid;  
    private Integer attributesearchtypeid;  

	// Constructor
	public AttributetypesearchtypeResource() {
		// Needed empty constructor for serialization
	}

	public Integer getAttributetypeid() {
		return this.attributetypeid;
	}
	public void setAttributetypeid(Integer attributetypeid) {
		this.attributetypeid = attributetypeid;
	}
	public Integer getAttributesearchtypeid() {
		return this.attributesearchtypeid;
	}
	public void setAttributesearchtypeid(Integer attributesearchtypeid) {
		this.attributesearchtypeid = attributesearchtypeid;
	}
}
