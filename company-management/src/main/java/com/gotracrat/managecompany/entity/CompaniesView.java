package com.gotracrat.managecompany.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "vw_companies")
public class CompaniesView implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name= "companyId")
	private Integer companyid;
	@Column(name = "name")
	private String name;
	@Column(name = "phone")
	private String phone;
	@Column(name = "city")
	private String city;
	@Column(name="filePath")
	private String filePath;	
	
}
