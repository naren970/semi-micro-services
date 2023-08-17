package com.gotracrat.managecompany.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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

import lombok.Getter;
import lombok.Setter;

/**
 * Persistent class for Type entity stored in table Type.
 * This class is a "record entity" without JPA links.
 * @author prabhakar
 * since 2018-05-25
 */
@Getter
@Setter
@Entity
@Table(name = "Type", schema = "dbo")
public class Type implements Serializable, Comparable<Type> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeID", nullable = false)
    private Integer typeid;   
    @Column(name = "Name", nullable = false, length = 100)
    private String name;
    @Column(name = "Description", length = 2000)
    private String description;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "ParentID", referencedColumnName = "TypeID", nullable = false)
    @JsonIgnore
    @NotFound(action = NotFoundAction.IGNORE)
    private Type type;
    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "ParentID")
    private List<Type> typeList = new ArrayList<>();
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
    public Type() {
        super();
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(typeid);
        sb.append(name);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(company);
        sb.append("|");
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
        if (this == o) { 
        	return true;
        }
        if (o == null || getClass() != o.getClass()) {
        	return false;
        }
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