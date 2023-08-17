/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:54 )
 * 
 */
package com.gotracrat.attributesandtypes.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

/**
 * Persistent class for Announcement entity stored in table Announcement.
 * This class is a "record entity" without JPA links.
 *
 * @author prabhakar
 */
@Entity
@Table(name = "Announcement", schema = "dbo")
public class Announcement implements Serializable, Comparable<Announcement> {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AnnouncementID", nullable = false)
    private Integer announcementid;
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    /*@Column(name = "CompanyID")
    private Integer companyid;
*/

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Company getCompany() {
        return company;
    }

    public Announcement setCompany(Company company) {
        this.company = company;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CompanyID", nullable = false)
    @JsonBackReference
    private Company company;

    @Column(name = "AnnouncementText")
    private String announcementtext;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AnnouncementDate", nullable = false)
    private Date announcementdate;

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Announcement() {
        super();
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setAnnouncementid(Integer announcementid) {
        this.announcementid = announcementid;
    }

    public Integer getAnnouncementid() {
        return this.announcementid;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : CompanyID (int)


    //--- DATABASE MAPPING : AnnouncementText (ntext)
    public void setAnnouncementtext(String announcementtext) {
        this.announcementtext = announcementtext;
    }

    public String getAnnouncementtext() {
        return this.announcementtext;
    }

    //--- DATABASE MAPPING : AnnouncementDate (datetime)
    public void setAnnouncementdate(Date announcementdate) {
        this.announcementdate = announcementdate;
    }

    public Date getAnnouncementdate() {
        return this.announcementdate;
    }


    //----------------------------------------------------------------------
    //----------------------------------------------------------------------
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(announcementid);
        sb.append(company);
        sb.append("|");
        sb.append(announcementtext);
        sb.append("|");
        sb.append(announcementdate);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return Objects.equals(announcementid, that.announcementid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(announcementid);
    }

    @Override
    public int compareTo(Announcement announcement) {
        return this.announcementid.compareTo(announcement.getAnnouncementid());
    }
}