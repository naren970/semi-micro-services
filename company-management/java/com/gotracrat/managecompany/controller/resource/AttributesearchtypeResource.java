/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:48:29 )
 *
*/
package com.gotracrat.managecompany.resource.resource;

/**
 * Hateoas resource associated with Attributesearchtype.
 * @author prabhakar
 */
public class AttributesearchtypeResource {

    private Integer attributesearchtypeid;  
    private String searchtype;  
    private String lookupcode;  

	// Constructor
	public AttributesearchtypeResource() {
		// Needed empty constructor for serialization
	}

	public Integer getAttributesearchtypeid() {
		return this.attributesearchtypeid;
	}
	public void setAttributesearchtypeid(Integer attributesearchtypeid) {
		this.attributesearchtypeid = attributesearchtypeid;
	}
	public String getSearchtype() {
		return this.searchtype;
	}
	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}
	public String getLookupcode() {
		return this.lookupcode;
	}
	public void setLookupcode(String lookupcode) {
		this.lookupcode = lookupcode;
	}
}
