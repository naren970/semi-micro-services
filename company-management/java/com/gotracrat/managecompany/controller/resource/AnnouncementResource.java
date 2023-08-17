/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:54 )
 * 
*/
package com.gotracrat.managecompany.resource.resource;

import java.util.Date;

/**
 * Hateoas resource associated with Announcement.
 * @author prabhakar
 */
public class AnnouncementResource  {

    private Integer announcementid;  
    private Integer companyid;  
    private String announcementtext;  
    private Date announcementdate;  

	// Constructor
	public AnnouncementResource() {
		// Needed empty constructor for serialization
	}

	public Integer getAnnouncementid() {
		return this.announcementid;
	}
	public void setAnnouncementid(Integer announcementid) {
		this.announcementid = announcementid;
	}
	public Integer getCompanyid() {
		return this.companyid;
	}
	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}
	public String getAnnouncementtext() {
		return this.announcementtext;
	}
	public void setAnnouncementtext(String announcementtext) {
		this.announcementtext = announcementtext;
	}
	public Date getAnnouncementdate() {
		return this.announcementdate;
	}
	public void setAnnouncementdate(Date announcementdate) {
		this.announcementdate = announcementdate;
	}
}
