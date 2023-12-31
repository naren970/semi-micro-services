/*
 * Created on 2018-10-27 ( Date ISO 2018-10-27 - Time 19:57:22 )
 * Generated by 
*/
package com.gotracrat.managelocation.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Persistent class for AspnetUsers entity stored in table aspnet_Users. This
 * class is a "record entity" without JPA links.
 * 
 * @author Prabhakar
 */
@Entity
@Table(name = "aspnet_Users", schema = "dbo")
public class AspnetUsers implements Serializable {

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "UserId", nullable = false, length = 36)
	private String userid;
	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@Column(name = "ApplicationId", nullable = false, length = 36)
	private String applicationid;
	@Column(name = "UserName", nullable = false)
	private String username;
	@Column(name = "LoweredUserName", nullable = false)
	private String loweredusername;
	@Column(name = "MobileAlias")
	private String mobilealias;
	@Column(name = "IsAnonymous", nullable = false)
	private Boolean isanonymous;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LastActivityDate", nullable = false)
	private Date lastactivitydate;
	
	/*@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "aspnetUsers")
	private AspnetMembership aspnetMembership;*/
	
	/*@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "aspnetUsers")
	private Profile profile;
*/
	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public AspnetUsers() {
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserid() {
		return this.userid;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------
	// --- DATABASE MAPPING : ApplicationId (uniqueidentifier)
	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}

	public String getApplicationid() {
		return this.applicationid;
	}

	// --- DATABASE MAPPING : UserName (nvarchar)
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	// --- DATABASE MAPPING : LoweredUserName (nvarchar)
	public void setLoweredusername(String loweredusername) {
		this.loweredusername = loweredusername;
	}

	public String getLoweredusername() {
		return this.loweredusername;
	}

	// --- DATABASE MAPPING : MobileAlias (nvarchar)
	public void setMobilealias(String mobilealias) {
		this.mobilealias = mobilealias;
	}

	public String getMobilealias() {
		return this.mobilealias;
	}

	// --- DATABASE MAPPING : IsAnonymous (bit)
	public void setIsanonymous(Boolean isanonymous) {
		this.isanonymous = isanonymous;
	}

	public Boolean getIsanonymous() {
		return this.isanonymous;
	}

	// --- DATABASE MAPPING : LastActivityDate (datetime)
	public void setLastactivitydate(Date lastactivitydate) {
		this.lastactivitydate = lastactivitydate;
	}

	public Date getLastactivitydate() {
		return this.lastactivitydate;
	}

	/*public AspnetMembership getAspnetMembership() {
		return aspnetMembership;
	}
	public void setAspnetMembership(AspnetMembership aspnetMembership) {
		this.aspnetMembership = aspnetMembership;
	}*/

	/*public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}*/

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(userid);
		sb.append(applicationid);
		sb.append("|");
		sb.append(username);
		sb.append("|");
		sb.append(loweredusername);
		sb.append("|");
		sb.append(mobilealias);
		sb.append("|");
		sb.append(isanonymous);
		sb.append("|");
		sb.append(lastactivitydate);
		return sb.toString();
	}
}
