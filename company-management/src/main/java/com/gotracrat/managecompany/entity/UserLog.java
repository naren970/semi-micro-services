/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:57 )
 *
 */
package com.gotracrat.managecompany.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Persistent class for UserLog entity stored in table UserLog. This class is a
 * "record entity" without JPA links.
 *
 * @author ram
 */
@Entity
@Data
@Table(name = "UserLog", schema = "dbo")
public class UserLog implements Serializable {

    private static final long serialVersionUID = 1L;

    // ----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    // ----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserLogId", nullable = false)
    private Integer userLogId;
    // ----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    // ----------------------------------------------------------------------
    @Column(name = "UserName", nullable = false, length = 200)
    private String userName;

    @Column(name = "CompanyID", nullable = false)
    private Integer companyID;

    @Column(name = "UserAction", nullable = false, length = 200)
    private String userAction;

    @Column(name = "ActionComment", nullable = false, length = 200)
    private String actionComment;

    @Column(name = "LogXML")
    private String logXml;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LogDate", nullable = false)
    private Date logDate;

    @Transient
    private boolean canNotInsert;

    // ----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    // ----------------------------------------------------------------------
    public UserLog() {
        super();
    }

    // ----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    // ----------------------------------------------------------------------


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    // ----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    // ----------------------------------------------------------------------

    public Integer getUserLogId() {
        return userLogId;
    }

    public void setUserLogId(Integer userLogId) {
        this.userLogId = userLogId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public String getUserAction() {
        return userAction;
    }

    public void setUserAction(String userAction) {
        this.userAction = userAction;
    }

    public String getActionComment() {
        return actionComment;
    }

    public void setActionComment(String actionComment) {
        this.actionComment = actionComment;
    }

    public String getLogXml() {
        return logXml;
    }

    public void setLogXml(String logXml) {
        this.logXml = logXml;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    // ----------------------------------------------------------------------
    // toString METHOD
    // ----------------------------------------------------------------------
    @Override
    public String toString() {
        return "UserLog{" +
                "userLogId=" + userLogId +
                ", userName='" + userName + '\'' +
                ", companyID=" + companyID +
                ", userAction='" + userAction + '\'' +
                ", actionComment='" + actionComment + '\'' +
                ", logXml='" + logXml + '\'' +
                ", logDate=" + logDate +
                '}';
    }

    // --- DATABASE MAPPING : Name (varchar)





}