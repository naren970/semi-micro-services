package com.gotracrat.managelocation.resource;

import java.util.Date;

public class LogManagementResource {

	    
	    private String userid;  
	    private String username;  
	    private String firstname;
	    private String lastname;
	    private Boolean isloggedin;
		private Integer logincount;  
		private Date lastlogindate;  
		private Date lastLogoutDate;  
	    private Boolean isOwnerAdmin;

	    public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public Boolean getIsloggedin() {
			return isloggedin;
		}
		public void setIsloggedin(Boolean isloggedin) {
			this.isloggedin = isloggedin;
		}
		public Integer getLogincount() {
			return logincount;
		}
		public void setLogincount(Integer logincount) {
			this.logincount = logincount;
		}
		public Date getLastlogindate() {
			return lastlogindate;
		}
		public void setLastlogindate(Date lastlogindate) {
			this.lastlogindate = lastlogindate;
		}
		public Boolean getIsOwnerAdmin() {
			return isOwnerAdmin;
		}
		public void setIsOwnerAdmin(Boolean isOwnerAdmin) {
			this.isOwnerAdmin = isOwnerAdmin;
		}
		public Date getLastLogoutDate() {
			return lastLogoutDate;
		}
		public void setLastLogoutDate(Date lastLogoutDate) {
			this.lastLogoutDate = lastLogoutDate;
		}
		}
