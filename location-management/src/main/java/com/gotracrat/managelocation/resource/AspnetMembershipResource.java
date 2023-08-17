/*
 * Created on 2018-10-27 ( Date ISO 2018-10-27 - Time 19:57:22 )
 * Generated by 
*/
package com.gotracrat.managelocation.resource;

import java.util.Date;

/**
 * Hateoas resource associated with AspnetMembership.
 * @author Prabhakar
 */
public class AspnetMembershipResource  {

    private String applicationid;  
    private String userid;  
    private String password;  
    private String currentpassword;
    private String newpassword;
    private String confirmpassword;
	private Integer passwordformat;  
    private String passwordsalt;  
    private String mobilepin;  
    private String email;  
    private String loweredemail;  
    private String passwordquestion;  
    private String passwordanswer;  
    private Boolean isapproved;  
    private Boolean islockedout;  
    private Date createdate;  
    private Date lastlogindate;  
    private Date lastpasswordchangeddate;  
    private Date lastlockoutdate;  
    private Integer failedpasswordattemptcount;  
    private Date failedpasswordattemptwindowstart;  
    private Integer failedpasswordanswerattemptcount;  
    private Date failedpasswordanswerattemptwindowstart;  
    private String comment;  

	// Constructor
	public AspnetMembershipResource() {
		// Needed empty constructor for serialization
	}

	public String getApplicationid() {
		return this.applicationid;
	}
	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}
	public String getUserid() {
		return this.userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getPasswordformat() {
		return this.passwordformat;
	}
	public void setPasswordformat(Integer passwordformat) {
		this.passwordformat = passwordformat;
	}
	public String getPasswordsalt() {
		return this.passwordsalt;
	}
	public void setPasswordsalt(String passwordsalt) {
		this.passwordsalt = passwordsalt;
	}
	public String getMobilepin() {
		return this.mobilepin;
	}
	public void setMobilepin(String mobilepin) {
		this.mobilepin = mobilepin;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLoweredemail() {
		return this.loweredemail;
	}
	public void setLoweredemail(String loweredemail) {
		this.loweredemail = loweredemail;
	}
	public String getPasswordquestion() {
		return this.passwordquestion;
	}
	public void setPasswordquestion(String passwordquestion) {
		this.passwordquestion = passwordquestion;
	}
	public String getPasswordanswer() {
		return this.passwordanswer;
	}
	public void setPasswordanswer(String passwordanswer) {
		this.passwordanswer = passwordanswer;
	}
	public Boolean getIsapproved() {
		return this.isapproved;
	}
	public void setIsapproved(Boolean isapproved) {
		this.isapproved = isapproved;
	}
	public Boolean getIslockedout() {
		return this.islockedout;
	}
	public void setIslockedout(Boolean islockedout) {
		this.islockedout = islockedout;
	}
	public Date getCreatedate() {
		return this.createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getLastlogindate() {
		return this.lastlogindate;
	}
	public void setLastlogindate(Date lastlogindate) {
		this.lastlogindate = lastlogindate;
	}
	public Date getLastpasswordchangeddate() {
		return this.lastpasswordchangeddate;
	}
	public void setLastpasswordchangeddate(Date lastpasswordchangeddate) {
		this.lastpasswordchangeddate = lastpasswordchangeddate;
	}
	public Date getLastlockoutdate() {
		return this.lastlockoutdate;
	}
	public void setLastlockoutdate(Date lastlockoutdate) {
		this.lastlockoutdate = lastlockoutdate;
	}
	public Integer getFailedpasswordattemptcount() {
		return this.failedpasswordattemptcount;
	}
	public void setFailedpasswordattemptcount(Integer failedpasswordattemptcount) {
		this.failedpasswordattemptcount = failedpasswordattemptcount;
	}
	public Date getFailedpasswordattemptwindowstart() {
		return this.failedpasswordattemptwindowstart;
	}
	public void setFailedpasswordattemptwindowstart(Date failedpasswordattemptwindowstart) {
		this.failedpasswordattemptwindowstart = failedpasswordattemptwindowstart;
	}
	public Integer getFailedpasswordanswerattemptcount() {
		return this.failedpasswordanswerattemptcount;
	}
	public void setFailedpasswordanswerattemptcount(Integer failedpasswordanswerattemptcount) {
		this.failedpasswordanswerattemptcount = failedpasswordanswerattemptcount;
	}
	public Date getFailedpasswordanswerattemptwindowstart() {
		return this.failedpasswordanswerattemptwindowstart;
	}
	public void setFailedpasswordanswerattemptwindowstart(Date failedpasswordanswerattemptwindowstart) {
		this.failedpasswordanswerattemptwindowstart = failedpasswordanswerattemptwindowstart;
	}
	public String getComment() {
		return this.comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	 public String getCurrentpassword() {
			return currentpassword;
		}

		public void setCurrentpassword(String currentpassword) {
			this.currentpassword = currentpassword;
		}

		public String getNewpassword() {
			return newpassword;
		}

		public void setNewpassword(String newpassword) {
			this.newpassword = newpassword;
		}

		public String getConfirmpassword() {
			return confirmpassword;
		}

		public void setConfirmpassword(String confirmpassword) {
			this.confirmpassword = confirmpassword;
		}
}