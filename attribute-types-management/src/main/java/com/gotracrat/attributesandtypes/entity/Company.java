/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:57 )
 * 
 */
package com.gotracrat.attributesandtypes.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Persistent class for Company entity stored in table Company. This class is a
 * "record entity" without JPA links.
 *
 * @author prabhakar
 */
@Entity
@Table(name = "Company", schema = "dbo")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    // ----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    // ----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CompanyID", nullable = false)
    private Integer companyid;
    // ----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    // ----------------------------------------------------------------------
    @Column(name = "Name", nullable = false, length = 200)
    private String name;
    @Column(name = "Address1", length = 100)
    private String address1;
    @Column(name = "Address2", length = 100)
    private String address2;
    @Column(name = "City", length = 50)
    private String city;
    @Column(name = "State", length = 2)
    private String state;
    @Column(name = "PostalCode", length = 10)
    private String postalcode;
    @Column(name = "Phone", length = 14)
    private String phone;
    @Column(name = "Fax", length = 14)
    private String fax;
    @Column(name = "URL", length = 100)
    private String url;
    @Column(name = "Description", length = 2000)
    private String description;
    @Column(name = "StatusID")
    private Integer statusid;
    @Column(name = "LastModifiedBy", length = 255)
    private String lastmodifiedby;
    @Column(name = "CompanyFileName")
    private String companyfilename;
    @Column(name = "CompanyContentType")
    private String companycontenttype;
    @Lob
    @Column(name = "CompanyImage")
    private byte[] companyimage;
    @Column(name = "Vendor")
    private Boolean vendor;
    @Column(name = "SupplyLevelWarning", nullable = false)
    private Boolean supplylevelwarning;
    @Column(name = "ParentCompanyID")
    private Integer parentcompanyid;
    @Column(name = "IsSandbox", nullable = false)
    private Boolean issandbox;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "company")
    @OrderBy("announcementid DESC")
    @JsonManagedReference
    private List<Announcement> announcements;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "company")
    @JsonIgnore
    @OrderBy("typeid DESC")
    private List<Type> types;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "company")
    @JsonManagedReference
    @OrderBy("statusid DESC")
    private List<Status> statuses;

    public String getTempImage() {
        return tempImage;
    }

    public void setTempImage(String tempImage) {
        this.tempImage = tempImage;
    }

    @Transient
    private String tempImage;

    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }


    // ----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    // ----------------------------------------------------------------------
    public Company() {
        super();
    }

    // ----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    // ----------------------------------------------------------------------
    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public Integer getCompanyid() {
        return this.companyid;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    // ----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    // ----------------------------------------------------------------------
    // --- DATABASE MAPPING : Name (varchar)
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // --- DATABASE MAPPING : Address1 (varchar)
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress1() {
        return this.address1;
    }

    // --- DATABASE MAPPING : Address2 (varchar)
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress2() {
        return this.address2;
    }

    // --- DATABASE MAPPING : City (varchar)
    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }

    // --- DATABASE MAPPING : State (char)
    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    // --- DATABASE MAPPING : PostalCode (varchar)
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPostalcode() {
        return this.postalcode;
    }

    // --- DATABASE MAPPING : Phone (varchar)
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    // --- DATABASE MAPPING : Fax (varchar)
    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFax() {
        return this.fax;
    }

    // --- DATABASE MAPPING : URL (varchar)
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    // --- DATABASE MAPPING : Description (varchar)
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    // --- DATABASE MAPPING : StatusID (int)
    public void setStatusid(Integer statusid) {
        this.statusid = statusid;
    }

    public Integer getStatusid() {
        return this.statusid;
    }

    // --- DATABASE MAPPING : LastModifiedBy (varchar)
    public void setLastmodifiedby(String lastmodifiedby) {
        this.lastmodifiedby = lastmodifiedby;
    }

    public String getLastmodifiedby() {
        return this.lastmodifiedby;
    }

    // --- DATABASE MAPPING : CompanyFileName (nvarchar)
    public void setCompanyfilename(String companyfilename) {
        this.companyfilename = companyfilename;
    }

    public String getCompanyfilename() {
        return this.companyfilename;
    }

    // --- DATABASE MAPPING : CompanyContentType (nvarchar)
    public void setCompanycontenttype(String companycontenttype) {
        this.companycontenttype = companycontenttype;
    }

    public String getCompanycontenttype() {
        return this.companycontenttype;
    }

    // --- DATABASE MAPPING : CompanyImage (image)
    public void setCompanyimage(byte[] companyimage) {
        this.companyimage = companyimage;
    }

    public byte[] getCompanyimage() {
        return this.companyimage;
    }

    // --- DATABASE MAPPING : Vendor (bit)
    public void setVendor(Boolean vendor) {
        this.vendor = vendor;
    }

    public Boolean getVendor() {
        return this.vendor;
    }

    // --- DATABASE MAPPING : SupplyLevelWarning (bit)
    public void setSupplylevelwarning(Boolean supplylevelwarning) {
        this.supplylevelwarning = supplylevelwarning;
    }

    public Boolean getSupplylevelwarning() {
        return this.supplylevelwarning;
    }

    // --- DATABASE MAPPING : ParentCompanyID (int)
    public void setParentcompanyid(Integer parentcompanyid) {
        this.parentcompanyid = parentcompanyid;
    }

    public Integer getParentcompanyid() {
        return this.parentcompanyid;
    }

    // --- DATABASE MAPPING : IsSandbox (bit)
    public void setIssandbox(Boolean issandbox) {
        this.issandbox = issandbox;
    }

    public Boolean getIssandbox() {
        return this.issandbox;
    }

    // ----------------------------------------------------------------------
    // toString METHOD
    // ----------------------------------------------------------------------
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(companyid);
        sb.append(name);
        sb.append("|");
        sb.append(address1);
        sb.append("|");
        sb.append(address2);
        sb.append("|");
        sb.append(city);
        sb.append("|");
        sb.append(state);
        sb.append("|");
        sb.append(postalcode);
        sb.append("|");
        sb.append(phone);
        sb.append("|");
        sb.append(fax);
        sb.append("|");
        sb.append(url);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(statusid);
        sb.append("|");
        //sb.append(lastmodifiedby);
        sb.append("|");
        //sb.append(companyfilename);
        sb.append("|");
        //sb.append(companycontenttype);
        sb.append("|");
        //sb.append(companyimage);
        sb.append("|");
        sb.append(vendor);
        sb.append("|");
        sb.append(supplylevelwarning);
        sb.append("|");
        sb.append(parentcompanyid);
        sb.append("|");
        //sb.append(issandbox);
        return sb.toString();
    }
}