/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:57 )
 * 
 */
package com.gotracrat.managelocation.resource;

import java.io.Serializable;

/**
 * Hateoas resource associated with Company.
 *
 * @author prabhakar
 */
public class CompanyResource implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer companyid;
    private String name;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postalcode;
    private String phone;
    private String fax;
    private String url;
    private String description;
    private Integer statusid;
    private String lastmodifiedby;
    private String companyfilename;
    private String companycontenttype;
    private String companyimage;
    private Boolean vendor;
    private Boolean supplylevelwarning;
    private Integer parentcompanyid;
    private Boolean issandbox;

 /*   private Announcement announcement;
    private Type type;
    private Status status;*/

   /* public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }*/

    // Constructor
    public CompanyResource() {
        // Needed empty constructor for serialization
    }


    public String getCompanyimage() {
        return companyimage;
    }

    public void setCompanyimage(String companyimage) {
        this.companyimage = companyimage;
    }

    public Integer getCompanyid() {
        return this.companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return this.address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalcode() {
        return this.postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatusid() {
        return this.statusid;
    }

    public void setStatusid(Integer statusid) {
        this.statusid = statusid;
    }

    public String getLastmodifiedby() {
        return this.lastmodifiedby;
    }

    public void setLastmodifiedby(String lastmodifiedby) {
        this.lastmodifiedby = lastmodifiedby;
    }

    public String getCompanyfilename() {
        return this.companyfilename;
    }

    public void setCompanyfilename(String companyfilename) {
        this.companyfilename = companyfilename;
    }

    public String getCompanycontenttype() {
        return this.companycontenttype;
    }

    public void setCompanycontenttype(String companycontenttype) {
        this.companycontenttype = companycontenttype;
    }


    public Boolean getVendor() {
        return this.vendor;
    }

    public void setVendor(Boolean vendor) {
        this.vendor = vendor;
    }

    public Boolean getSupplylevelwarning() {
        return this.supplylevelwarning;
    }

    public void setSupplylevelwarning(Boolean supplylevelwarning) {
        this.supplylevelwarning = supplylevelwarning;
    }

    public Integer getParentcompanyid() {
        return this.parentcompanyid;
    }

    public void setParentcompanyid(Integer parentcompanyid) {
        this.parentcompanyid = parentcompanyid;
    }

    public Boolean getIssandbox() {
        return this.issandbox;
    }

    public void setIssandbox(Boolean issandbox) {
        this.issandbox = issandbox;
    }
}
