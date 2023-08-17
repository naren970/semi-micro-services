/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:59 )
 * 
*/
package com.gotracrat.managecompany.resource;

import java.io.Serializable;

/**
 * Hateoas resource associated with Status.
 * @author prabhakar
 */
public class StatusResource  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer statusid;  
    private Integer entitytypeid;  
    private String status;  
    private Integer companyid;  
    private Boolean inservice;  
    private Boolean underrepair;  
    private Boolean spare;  
    private Boolean destroyed;  

	// Constructor
	public StatusResource() {
		// Needed empty constructor for serialization
	}

	public Integer getStatusid() {
		return this.statusid;
	}
	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}
	public Integer getEntitytypeid() {
		return this.entitytypeid;
	}
	public void setEntitytypeid(Integer entitytypeid) {
		this.entitytypeid = entitytypeid;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCompanyid() {
		return this.companyid;
	}
	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}
	public Boolean getInservice() {
		return this.inservice;
	}
	public void setInservice(Boolean inservice) {
		this.inservice = inservice;
	}
	public Boolean getUnderrepair() {
		return this.underrepair;
	}
	public void setUnderrepair(Boolean underrepair) {
		this.underrepair = underrepair;
	}
	public Boolean getSpare() {
		return this.spare;
	}
	public void setSpare(Boolean spare) {
		this.spare = spare;
	}
	public Boolean getDestroyed() {
		return this.destroyed;
	}
	public void setDestroyed(Boolean destroyed) {
		this.destroyed = destroyed;
	}
}
