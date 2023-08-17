/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:58 )
 * 
*/
package com.gotracrat.managecompany.resource;

/**
 * Hateoas resource associated with Journaltype.
 * @author prabhakar
 */
public class JournaltypeResource  {

    private Integer journaltypeid;  
    private String description;  
    private String lookupcode;  

	// Constructor
	public JournaltypeResource() {
		// Needed empty constructor for serialization
	}

	public Integer getJournaltypeid() {
		return this.journaltypeid;
	}
	public void setJournaltypeid(Integer journaltypeid) {
		this.journaltypeid = journaltypeid;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLookupcode() {
		return this.lookupcode;
	}
	public void setLookupcode(String lookupcode) {
		this.lookupcode = lookupcode;
	}
}
