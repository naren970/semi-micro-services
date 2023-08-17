/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:59 )
 * 
 */
package com.gotracrat.managecompany.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Persistent class for Status entity stored in table Status.
 * This class is a "record entity" without JPA links.
 *
 * @author prabhakar
 */
@Entity
@Table(name = "Status", schema = "dbo")
public class Status implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StatusID", nullable = false)
    private Integer statusid;
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name = "EntityTypeID", nullable = false)
    private Integer entitytypeid;
    @Column(name = "Status", nullable = false, length = 100)
    private String status;

    @Column(name = "InService", nullable = false)
    private Boolean inservice;
    @Column(name = "UnderRepair", nullable = false)
    private Boolean underrepair;
    @Column(name = "Spare", nullable = false)
    private Boolean spare;
    @Column(name = "Destroyed", nullable = false)
    private Boolean destroyed;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CompanyID")
    @JsonBackReference
    private Company company;

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Status() {
        super();
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setStatusid(Integer statusid) {
        this.statusid = statusid;
    }

    public Integer getStatusid() {
        return this.statusid;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : EntityTypeID (int)
    public void setEntitytypeid(Integer entitytypeid) {
        this.entitytypeid = entitytypeid;
    }

    public Integer getEntitytypeid() {
        return this.entitytypeid;
    }

    //--- DATABASE MAPPING : Status (varchar)
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    //--- DATABASE MAPPING : CompanyID (int)


    //--- DATABASE MAPPING : InService (bit)
    public void setInservice(Boolean inservice) {
        this.inservice = inservice;
    }

    public Boolean getInservice() {
        return this.inservice;
    }

    //--- DATABASE MAPPING : UnderRepair (bit)
    public void setUnderrepair(Boolean underrepair) {
        this.underrepair = underrepair;
    }

    public Boolean getUnderrepair() {
        return this.underrepair;
    }

    //--- DATABASE MAPPING : Spare (bit)
    public void setSpare(Boolean spare) {
        this.spare = spare;
    }

    public Boolean getSpare() {
        return this.spare;
    }

    //--- DATABASE MAPPING : Destroyed (bit)
    public void setDestroyed(Boolean destroyed) {
        this.destroyed = destroyed;
    }

    public Boolean getDestroyed() {
        return this.destroyed;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(statusid);
        sb.append(entitytypeid);
        sb.append("|");
        sb.append(status);
        sb.append("|");
        sb.append(company);
        sb.append("|");
        sb.append(inservice);
        sb.append("|");
        sb.append(underrepair);
        sb.append("|");
        sb.append(spare);
        sb.append("|");
        sb.append(destroyed);
        return sb.toString();
    }
}