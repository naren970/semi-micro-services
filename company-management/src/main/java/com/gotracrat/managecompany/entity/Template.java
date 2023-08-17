package com.gotracrat.managecompany.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "TemplateID", nullable = false)
	    private Integer templateID;
	    @Column(name = "Name", nullable = false, length = 255)
	    private String name;
	    @Column(name = "Xml")
	    private String Xml;
	    @Column(name = "CompanyID", nullable = false)
	    private Integer companyID;
}
