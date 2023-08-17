/*
 * Created on 2018-10-27 ( Date ISO 2018-10-27 - Time 19:57:25 )
 * Generated by 
*/
package com.gotracrat.managelocation.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Persistent class for Usersecurity entity stored in table UserSecurity.
 * This class is a "record entity" without JPA links.
 * @author Prabhakar
 */
@Entity
@AllArgsConstructor
@Table(name="UserSecurity", schema="dbo" )
public class UserSecurity implements Serializable {

	private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( EMBEDDED IN AN EXTERNAL CLASS )  
    //----------------------------------------------------------------------
	@EmbeddedId
    private UsersecurityKey compositePrimaryKey ;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="RoleID", nullable=false, length=36)
    private String roleid ; 

    @Column(name="lastmodifiedby", nullable=false, length=256)
    private String lastmodifiedby ; 
    public String getLastmodifiedby() {
		return lastmodifiedby;
	}

	public void setLastmodifiedby(String lastmodifiedby) {
		this.lastmodifiedby = lastmodifiedby;
	}

	//----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public UserSecurity() {
		super();
		this.compositePrimaryKey = new UsersecurityKey();       
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
	public void setUserid(String userid) {
        this.compositePrimaryKey.setUserid(userid) ;
    }
	public String getUserid() {
        return this.compositePrimaryKey.getUserid() ;
    }
	public void setCompanyid(Integer companyid) {
        this.compositePrimaryKey.setCompanyid(companyid) ;
    }
	public Integer getCompanyid() {
        return this.compositePrimaryKey.getCompanyid() ;
    }
	public void setLocationid(Integer locationid) {
        this.compositePrimaryKey.setLocationid(locationid) ;
    }
	public Integer getLocationid() {
        return this.compositePrimaryKey.getLocationid() ;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
	//--- DATABASE MAPPING : RoleID (uniqueidentifier) 
	public void setRoleid(String roleid) {this.roleid = roleid;}
	public String getRoleid() {return this.roleid;}


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append("[" + compositePrimaryKey + "]"); 
		sb.append(roleid); 
        return sb.toString();
    }
}