/*
 * Created on 2018-10-27 ( Date ISO 2018-10-27 - Time 19:57:22 )
 * Generated by
 */
package com.gotracrat.managelocation.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Persistent class for AspnetMembership entity stored in table aspnet_Membership.
 * This class is a "record entity" without JPA links.
 *
 * @author Prabhakar
 */
@Entity
@Table(name = "aspnet_Membership", schema = "dbo")
public class AspnetMembership implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name = "UserId", nullable = false, length = 36)
    private String userid;
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name = "ApplicationId", nullable = false, length = 36)
    private String applicationid;
    @Column(name = "Password")
    private String password;
    @Column(name = "PasswordFormat")
    private Integer passwordformat;
    @Column(name = "PasswordSalt")
    private String passwordsalt;
    @Column(name = "MobilePIN")
    private String mobilepin;
    @Column(name = "Email")
    private String email;
    @Column(name = "LoweredEmail")
    private String loweredemail;
    @Column(name = "PasswordQuestion")
    private String passwordquestion;
    @Column(name = "PasswordAnswer")
    private String passwordanswer;
    @Column(name = "IsApproved", nullable = false)
    private Boolean isapproved;
    @Column(name = "IsLockedOut", nullable = false)
    private Boolean islockedout;
    @Column(name = "IsLoggedIn", nullable = false)
    private Boolean IsLoggedIn;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreateDate", nullable = false)
    private Date createdate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastLoginDate", nullable = false)
    private Date lastlogindate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastLogoutDate", nullable = false)
    private Date lastlogoutdate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastPasswordChangedDate", nullable = false)
    private Date lastpasswordchangeddate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastLockoutDate", nullable = false)
    private Date lastlockoutdate;
    @Column(name = "FailedPasswordAttemptCount", nullable = false)
    private Integer failedpasswordattemptcount;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FailedPasswordAttemptWindowStart", nullable = false)
    private Date failedpasswordattemptwindowstart;
    @Column(name = "FailedPasswordAnswerAttemptCount", nullable = false)
    private Integer failedpasswordanswerattemptcount;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FailedPasswordAnswerAttemptWindowStart", nullable = false)
    private Date failedpasswordanswerattemptwindowstart;
    @Column(name = "Comment")
    private String comment;
    @Column(name = "reset_token")
    private String resetToken;
    
   /* @OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "UserId", nullable = false)
    private AspnetUsers aspnetUsers;*/

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public AspnetMembership() {
        super();
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return this.userid;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : ApplicationId (uniqueidentifier)
    public void setApplicationid(String applicationid) {
        this.applicationid = applicationid;
    }

    public String getApplicationid() {
        return this.applicationid;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    //--- DATABASE MAPPING : Password (nvarchar)
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    //--- DATABASE MAPPING : PasswordFormat (int)
    public void setPasswordformat(Integer passwordformat) {
        this.passwordformat = passwordformat;
    }

    public Integer getPasswordformat() {
        return this.passwordformat;
    }

    //--- DATABASE MAPPING : PasswordSalt (nvarchar)
    public void setPasswordsalt(String passwordsalt) {
        this.passwordsalt = passwordsalt;
    }

    public String getPasswordsalt() {
        return this.passwordsalt;
    }

    //--- DATABASE MAPPING : MobilePIN (nvarchar)
    public void setMobilepin(String mobilepin) {
        this.mobilepin = mobilepin;
    }

    public String getMobilepin() {
        return this.mobilepin;
    }

    //--- DATABASE MAPPING : Email (nvarchar)
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    //--- DATABASE MAPPING : LoweredEmail (nvarchar)
    public void setLoweredemail(String loweredemail) {
        this.loweredemail = loweredemail;
    }

    public String getLoweredemail() {
        return this.loweredemail;
    }

    //--- DATABASE MAPPING : PasswordQuestion (nvarchar)
    public void setPasswordquestion(String passwordquestion) {
        this.passwordquestion = passwordquestion;
    }

    public String getPasswordquestion() {
        return this.passwordquestion;
    }

    //--- DATABASE MAPPING : PasswordAnswer (nvarchar)
    public void setPasswordanswer(String passwordanswer) {
        this.passwordanswer = passwordanswer;
    }

    public String getPasswordanswer() {
        return this.passwordanswer;
    }

    //--- DATABASE MAPPING : IsApproved (bit)
    public void setIsapproved(Boolean isapproved) {
        this.isapproved = isapproved;
    }

    public Boolean getIsapproved() {
        return this.isapproved;
    }

    //--- DATABASE MAPPING : IsLockedOut (bit)
    public void setIslockedout(Boolean islockedout) {
        this.islockedout = islockedout;
    }

    public Boolean getIslockedout() {
        return this.islockedout;
    }

    //--- DATABASE MAPPING : CreateDate (datetime)
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getCreatedate() {
        return this.createdate;
    }

    //--- DATABASE MAPPING : LastLoginDate (datetime)
    public void setLastlogindate(Date lastlogindate) {
        this.lastlogindate = lastlogindate;
    }

    public Date getLastlogindate() {
        return this.lastlogindate;
    }

    //--- DATABASE MAPPING : LastPasswordChangedDate (datetime)
    public void setLastpasswordchangeddate(Date lastpasswordchangeddate) {
        this.lastpasswordchangeddate = lastpasswordchangeddate;
    }

    public Date getLastpasswordchangeddate() {
        return this.lastpasswordchangeddate;
    }

    //--- DATABASE MAPPING : LastLockoutDate (datetime)
    public void setLastlockoutdate(Date lastlockoutdate) {
        this.lastlockoutdate = lastlockoutdate;
    }

    public Date getLastlockoutdate() {
        return this.lastlockoutdate;
    }

    //--- DATABASE MAPPING : FailedPasswordAttemptCount (int)
    public void setFailedpasswordattemptcount(Integer failedpasswordattemptcount) {
        this.failedpasswordattemptcount = failedpasswordattemptcount;
    }

    public Integer getFailedpasswordattemptcount() {
        return this.failedpasswordattemptcount;
    }

    //--- DATABASE MAPPING : FailedPasswordAttemptWindowStart (datetime)
    public void setFailedpasswordattemptwindowstart(Date failedpasswordattemptwindowstart) {
        this.failedpasswordattemptwindowstart = failedpasswordattemptwindowstart;
    }

    public Date getFailedpasswordattemptwindowstart() {
        return this.failedpasswordattemptwindowstart;
    }

    //--- DATABASE MAPPING : FailedPasswordAnswerAttemptCount (int)
    public void setFailedpasswordanswerattemptcount(Integer failedpasswordanswerattemptcount) {
        this.failedpasswordanswerattemptcount = failedpasswordanswerattemptcount;
    }

    public Integer getFailedpasswordanswerattemptcount() {
        return this.failedpasswordanswerattemptcount;
    }

    //--- DATABASE MAPPING : FailedPasswordAnswerAttemptWindowStart (datetime)
    public void setFailedpasswordanswerattemptwindowstart(Date failedpasswordanswerattemptwindowstart) {
        this.failedpasswordanswerattemptwindowstart = failedpasswordanswerattemptwindowstart;
    }

    public Date getFailedpasswordanswerattemptwindowstart() {
        return this.failedpasswordanswerattemptwindowstart;
    }

    //--- DATABASE MAPPING : Comment (ntext)
    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return this.comment;
    }

    public Boolean getLoggedIn() {
        return IsLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        IsLoggedIn = loggedIn;
    }

    public Date getLastlogoutdate() {
        return lastlogoutdate;
    }

    public void setLastlogoutdate(Date lastlogoutdate) {
        this.lastlogoutdate = lastlogoutdate;
    }

    /* public AspnetUsers getAspnetUsers() {
		return aspnetUsers;
	}
	public void setAspnetUsers(AspnetUsers aspnetUsers) {
		this.aspnetUsers = aspnetUsers;
	}*/

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(userid);
        sb.append(applicationid);
        sb.append("|");
        sb.append(password);
        sb.append("|");
        sb.append(passwordformat);
        sb.append("|");
        sb.append(passwordsalt);
        sb.append("|");
        sb.append(mobilepin);
        sb.append("|");
        sb.append(email);
        sb.append("|");
        sb.append(loweredemail);
        sb.append("|");
        sb.append(passwordquestion);
        sb.append("|");
        sb.append(passwordanswer);
        sb.append("|");
        sb.append(isapproved);
        sb.append("|");
        sb.append(islockedout);
        sb.append("|");
        sb.append(createdate);
        sb.append("|");
        sb.append(lastlogindate);
        sb.append("|");
        sb.append(lastlogoutdate);
        sb.append("|");
        sb.append(IsLoggedIn);
        sb.append("|");
        sb.append(lastpasswordchangeddate);
        sb.append("|");
        sb.append(lastlockoutdate);
        sb.append("|");
        sb.append(failedpasswordattemptcount);
        sb.append("|");
        sb.append(failedpasswordattemptwindowstart);
        sb.append("|");
        sb.append(failedpasswordanswerattemptcount);
        sb.append("|");
        sb.append(failedpasswordanswerattemptwindowstart);
        sb.append("|");
        sb.append(comment);
        return sb.toString();
    }
}