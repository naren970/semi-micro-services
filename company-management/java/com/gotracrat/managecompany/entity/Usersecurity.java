package com.gotracrat.managecompany.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/*
 * Persistent class for Usersecurity entity stored in table UserSecurity. This
 * class is a "record entity" without JPA links.
 * @author Prabhakar
 * since 2018-10-27
 */
@Setter
@Getter
@Entity
@Table(name = "UserSecurity", schema = "dbo")
public class Usersecurity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsersecurityKey compositePrimaryKey;

	@Column(name = "RoleID", nullable = false, length = 36)
	private String roleid;

	public Usersecurity() {
		super();
		this.compositePrimaryKey = new UsersecurityKey();
	}

	public void setUserid(String userid) {
		this.compositePrimaryKey.setUserid(userid);
	}

	public String getUserid() {
		return this.compositePrimaryKey.getUserid();
	}

	public void setCompanyid(Integer companyid) {
		this.compositePrimaryKey.setCompanyid(companyid);
	}

	public Integer getCompanyid() {
		return this.compositePrimaryKey.getCompanyid();
	}

	public void setLocationid(Integer locationid) {
		this.compositePrimaryKey.setLocationid(locationid);
	}

	public Integer getLocationid() {
		return this.compositePrimaryKey.getLocationid();
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRoleid() {
		return this.roleid;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[" + compositePrimaryKey + "]");
		sb.append(roleid);
		return sb.toString();
	}
}