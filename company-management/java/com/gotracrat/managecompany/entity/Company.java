package com.gotracrat.managecompany.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

/**
 * Persistent class for Company entity stored in table Company. This class is a
 * "record entity" without JPA links.
 * @author prabhakar
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:57 )
 */
@Getter
@Setter
@Entity
@Table(name = "Company", schema = "dbo")
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CompanyID", nullable = false)
	private Integer companyid;
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
	@Column(name = "LastModifiedBy")
	private String lastmodifiedby;
	@Column(name = "CompanyFileName")
	private String companyfilename;
	@Column(name = "CompanyContentType")
	private String companycontenttype;
	@Lob
	@Column(name = "CompanyImage")
	private byte[] companyimageInBytes;
	@Transient
	private String companyimage;
	@Column(name = "Vendor")
	private Boolean vendor;
	@Column(name = "SupplyLevelWarning", nullable = false)
	private Boolean supplylevelwarning;
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "ParentCompanyID", referencedColumnName = "CompanyID", nullable = false)
	@JsonIgnore
	@NotFound(action = NotFoundAction.IGNORE)
	private Company parentcompany;
	@OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "ParentCompanyID")
	private List<Company> parentCompanyList =new ArrayList<>();
	@Column(name = "IsSandbox", nullable = false)
	private Boolean issandbox;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "company")
	@OrderBy("announcementid DESC")
	@JsonManagedReference
	private List<Announcement> announcements =new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "company")
	@JsonIgnore
	@OrderBy("typeid DESC")
	private List<Type> types = new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "company")
	@JsonManagedReference
	@OrderBy("statusid DESC")
	private List<Status> statuses =new ArrayList<>();
	@Column(name = "filePath", length = 2000)
    private String filePath;


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
		sb.append("|");
		sb.append("|");
		sb.append("|");
		sb.append("|");
		sb.append(vendor);
		sb.append("|");
		sb.append(supplylevelwarning);
		sb.append("|");
		sb.append("|");
		return sb.toString();
	}
}
