package com.gotracrat.managecompany.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Persistent class for Template entity stored in table Template. 
 * Since:
 * @author:manikanta
 */
@Getter
@Setter
@Entity
@Table(name = "Template", schema = "dbo")
public class Template {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "TemplateID", nullable = false)
	    private Integer templateID;
	    @Column(name = "Name", nullable = false, length = 255)
	    private String name;
	    @Column(name = "Xml")
	    private String Xml;
	    @Column(name = "CompanyID", nullable = false)
	    private Integer companyID;
}
