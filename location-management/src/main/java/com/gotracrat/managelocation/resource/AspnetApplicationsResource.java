/*
 * Created on 2018-10-27 ( Date ISO 2018-10-27 - Time 19:57:21 )
 * Generated by 
*/
package com.gotracrat.managelocation.resource;

/**
 * Hateoas resource associated with AspnetApplications.
 * @author Prabhakar
 */
public class AspnetApplicationsResource  {

    private String applicationname;  
    private String loweredapplicationname;  
    private String applicationid;  
    private String description;  

	// Constructor
	public AspnetApplicationsResource() {
		// Needed empty constructor for serialization
	}

	public String getApplicationname() {
		return this.applicationname;
	}
	public void setApplicationname(String applicationname) {
		this.applicationname = applicationname;
	}
	public String getLoweredapplicationname() {
		return this.loweredapplicationname;
	}
	public void setLoweredapplicationname(String loweredapplicationname) {
		this.loweredapplicationname = loweredapplicationname;
	}
	public String getApplicationid() {
		return this.applicationid;
	}
	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
