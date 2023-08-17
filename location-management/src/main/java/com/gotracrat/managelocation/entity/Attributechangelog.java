/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:55 )
 * 
*/
package com.gotracrat.managelocation.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Persistent class for Attributechangelog entity stored in table AttributeChangeLog.
 * This class is a "record entity" without JPA links.
 * @author prabhakar
 */
@Entity
@Table(name="AttributeChangeLog", schema="dbo" )
public class Attributechangelog implements Serializable {

	private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="AttributeChangeLogId", nullable=false)
    private Integer attributechangelogid ; 
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="LastModifiedBy", nullable=false)
    private String lastmodifiedby ;     @Column(name="AttributeNameId", nullable=false)
    private Integer attributenameid ;     @Column(name="EntityId", nullable=false)
    private Integer entityid ;     @Column(name="EntityTypeId", nullable=false)
    private Integer entitytypeid ;     @Column(name="OldValue", length=255)
    private String oldvalue ;     @Column(name="NewValue", length=255)
    private String newvalue ;     @Column(name="ChangeType", nullable=false, length=50)
    private String changetype ; 

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Attributechangelog() {
		super();
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
	public void setAttributechangelogid(Integer attributechangelogid) {
        this.attributechangelogid = attributechangelogid ;
    }
	public Integer getAttributechangelogid() {
        return this.attributechangelogid;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
	//--- DATABASE MAPPING : LastModifiedBy (nvarchar) 
	public void setLastmodifiedby(String lastmodifiedby) {this.lastmodifiedby = lastmodifiedby;}
	public String getLastmodifiedby() {return this.lastmodifiedby;}

	//--- DATABASE MAPPING : AttributeNameId (int) 
	public void setAttributenameid(Integer attributenameid) {this.attributenameid = attributenameid;}
	public Integer getAttributenameid() {return this.attributenameid;}

	//--- DATABASE MAPPING : EntityId (int) 
	public void setEntityid(Integer entityid) {this.entityid = entityid;}
	public Integer getEntityid() {return this.entityid;}

	//--- DATABASE MAPPING : EntityTypeId (int) 
	public void setEntitytypeid(Integer entitytypeid) {this.entitytypeid = entitytypeid;}
	public Integer getEntitytypeid() {return this.entitytypeid;}

	//--- DATABASE MAPPING : OldValue (varchar) 
	public void setOldvalue(String oldvalue) {this.oldvalue = oldvalue;}
	public String getOldvalue() {return this.oldvalue;}

	//--- DATABASE MAPPING : NewValue (varchar) 
	public void setNewvalue(String newvalue) {this.newvalue = newvalue;}
	public String getNewvalue() {return this.newvalue;}

	//--- DATABASE MAPPING : ChangeType (varchar) 
	public void setChangetype(String changetype) {this.changetype = changetype;}
	public String getChangetype() {return this.changetype;}


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(attributechangelogid); 
		sb.append(lastmodifiedby); 
		sb.append("|"); 
		sb.append(attributenameid); 
		sb.append("|"); 
		sb.append(entityid); 
		sb.append("|"); 
		sb.append(entitytypeid); 
		sb.append("|"); 
		sb.append(oldvalue); 
		sb.append("|"); 
		sb.append(newvalue); 
		sb.append("|"); 
		sb.append(changetype); 
        return sb.toString();
    }
}