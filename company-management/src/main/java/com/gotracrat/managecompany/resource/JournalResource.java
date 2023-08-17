/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:58 )
 * 
*/
package com.gotracrat.managecompany.resource;

import java.io.Serializable;
import java.util.Date;

/**
 * Hateoas resource associated with Journal.
 * @author prabhakar
 */
public class JournalResource implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer journalid;  
    private Integer entityid;  
    private Integer entitytypeid;  
    private String entry;  
    private String enteredby;  
    private Date enteredon;  
    private Date effectiveon;  
    private String entityxml;  
    private String entityname;  
    private Integer locationid;  
    private String locationname;  
    private Integer companyid;  
    private Integer journaltypeid;  
    private String jobnumber;  
    private String ponumber;  
    private String shippingnumber;  
    private String trackingnumber;  

	// Constructor
	public JournalResource() {
		// Needed empty constructor for serialization
	}

	public Integer getJournalid() {
		return this.journalid;
	}
	public void setJournalid(Integer journalid) {
		this.journalid = journalid;
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
	public String getEntry() {
		return this.entry;
	}
	public void setEntry(String entry) {
		this.entry = entry;
	}
	public String getEnteredby() {
		return this.enteredby;
	}
	public void setEnteredby(String enteredby) {
		this.enteredby = enteredby;
	}
	public Date getEnteredon() {
		return this.enteredon;
	}
	public void setEnteredon(Date enteredon) {
		this.enteredon = enteredon;
	}
	public Date getEffectiveon() {
		return this.effectiveon;
	}
	public void setEffectiveon(Date effectiveon) {
		this.effectiveon = effectiveon;
	}
	public String getEntityxml() {
		return this.entityxml;
	}
	public void setEntityxml(String entityxml) {
		this.entityxml = entityxml;
	}
	public String getEntityname() {
		return this.entityname;
	}
	public void setEntityname(String entityname) {
		this.entityname = entityname;
	}
	public Integer getLocationid() {
		return this.locationid;
	}
	public void setLocationid(Integer locationid) {
		this.locationid = locationid;
	}
	public String getLocationname() {
		return this.locationname;
	}
	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}
	public Integer getCompanyid() {
		return this.companyid;
	}
	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}
	public Integer getJournaltypeid() {
		return this.journaltypeid;
	}
	public void setJournaltypeid(Integer journaltypeid) {
		this.journaltypeid = journaltypeid;
	}
	public String getJobnumber() {
		return this.jobnumber;
	}
	public void setJobnumber(String jobnumber) {
		this.jobnumber = jobnumber;
	}
	public String getPonumber() {
		return this.ponumber;
	}
	public void setPonumber(String ponumber) {
		this.ponumber = ponumber;
	}
	public String getShippingnumber() {
		return this.shippingnumber;
	}
	public void setShippingnumber(String shippingnumber) {
		this.shippingnumber = shippingnumber;
	}
	public String getTrackingnumber() {
		return this.trackingnumber;
	}
	public void setTrackingnumber(String trackingnumber) {
		this.trackingnumber = trackingnumber;
	}
}
