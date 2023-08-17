/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:59 )
 *
 */
package com.gotracrat.attributesandtypes.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Persistent class for Type entity stored in table Type.
 * This class is a "record entity" without JPA links.
 *
 * @author prabhakar
 */
@Entity
@Table(name = "Type", schema = "dbo")
public class Type implements Serializable, Comparable<Type> {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeID", nullable = false)
    private Integer typeid;
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @Column(name = "Description", length = 2000)
    private String description;


    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "ParentID", referencedColumnName = "TypeID")
    @JsonIgnore
    @NotFound(action = NotFoundAction.IGNORE)
    private Type type;

    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "ParentID")
    private List<Type> typeList;

   /* @JoinColumn(name = "ParentID", referencedColumnName = "TypeID")
    private Integer parentid;*/

    @Column(name = "EntityTypeID", nullable = false)
    private Integer entitytypeid;

    @Column(name = "IsHidden", nullable = false)
    private Boolean ishidden;

    @Column(name = "LastModifiedBy", nullable = false)
    private String lastmodifiedby;

    @Column(name = "HostingFee", nullable = false)
    private BigDecimal hostingfee;

    @Column(name = "AttributeSearchDisplay")
    private Integer attributesearchdisplay;

    @Column(name = "TypeMTBS")
    private Integer typemtbs;

    @Column(name = "TypeSpareRatio")
    private BigDecimal typespareratio;


    @ManyToOne()
    @JoinColumn(name = "CompanyID")
    @JsonBackReference
    private Company company;

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Type() {
        super();
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getTypeid() {
        return this.typeid;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : Name (varchar)


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    //--- DATABASE MAPPING : Description (varchar)
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    //--- DATABASE MAPPING : EntityTypeID (int)
    public void setEntitytypeid(Integer entitytypeid) {
        this.entitytypeid = entitytypeid;
    }

    public Integer getEntitytypeid() {
        return this.entitytypeid;
    }

    //--- DATABASE MAPPING : CompanyID (int)


    //--- DATABASE MAPPING : IsHidden (bit)
    public void setIshidden(Boolean ishidden) {
        this.ishidden = ishidden;
    }

    public Boolean getIshidden() {
        return this.ishidden;
    }

    //--- DATABASE MAPPING : LastModifiedBy (nvarchar)
    public void setLastmodifiedby(String lastmodifiedby) {
        this.lastmodifiedby = lastmodifiedby;
    }

    public String getLastmodifiedby() {
        return this.lastmodifiedby;
    }

    //--- DATABASE MAPPING : HostingFee (money)
    public void setHostingfee(BigDecimal hostingfee) {
        this.hostingfee = hostingfee;
    }

    public BigDecimal getHostingfee() {
        return this.hostingfee;
    }

    //--- DATABASE MAPPING : AttributeSearchDisplay (int)
    public void setAttributesearchdisplay(Integer attributesearchdisplay) {
        this.attributesearchdisplay = attributesearchdisplay;
    }

    public Integer getAttributesearchdisplay() {
        return this.attributesearchdisplay;
    }

    //--- DATABASE MAPPING : TypeMTBS (int)
    public void setTypemtbs(Integer typemtbs) {
        this.typemtbs = typemtbs;
    }

    public Integer getTypemtbs() {
        return this.typemtbs;
    }

    //--- DATABASE MAPPING : TypeSpareRatio (decimal)
    public void setTypespareratio(BigDecimal typespareratio) {
        this.typespareratio = typespareratio;
    }

    public BigDecimal getTypespareratio() {
        return this.typespareratio;
    }

    /*public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}*/

	//----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(typeid);
        sb.append(name);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(company);
        sb.append("|");
        //sb.append(parentid);
        sb.append("|");
        sb.append(ishidden);
        sb.append("|");
        sb.append(lastmodifiedby);
        sb.append("|");
        sb.append(hostingfee);
        sb.append("|");
        sb.append(attributesearchdisplay);
        sb.append("|");
        sb.append(typemtbs);
        sb.append("|");
        sb.append(typespareratio);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return Objects.equals(typeid, type.typeid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeid);
    }

    @Override
    public int compareTo(Type o) {
        return this.typeid.compareTo(o.getTypeid());
    }
}