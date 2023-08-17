/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:56 )
 * 
*/
package com.gotracrat.attributesandtypes.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Persistent class for Attributevalue entity stored in table AttributeValue.
 * This class is a "record entity" without JPA links.
 * @author prabhakar
 */
@Entity
@Table(name="AttributeValue", schema="dbo" )
public class Attributevalue implements Serializable {

	private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( EMBEDDED IN AN EXTERNAL CLASS )  
    //----------------------------------------------------------------------
	@EmbeddedId
    private AttributevalueKey compositePrimaryKey ;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="Value", length=255)
    private String value ;     @Column(name="LastModifiedBy", nullable=false)
    private String lastmodifiedby ; 

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Attributevalue() {
		super();
		this.compositePrimaryKey = new AttributevalueKey();       
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------

	public AttributevalueKey getCompositePrimaryKey() {
		return compositePrimaryKey;
	}

	public void setCompositePrimaryKey(AttributevalueKey compositePrimaryKey) {
		this.compositePrimaryKey = compositePrimaryKey;
	}

	public void setEntityid(Integer entityid) {
		this.compositePrimaryKey.setEntityid(entityid);
	}

	public Integer getEntityid() {
		return this.compositePrimaryKey.getEntityid();
	}

	public void setEntitytypeid(Integer entitytypeid) {
		this.compositePrimaryKey.setEntitytypeid(entitytypeid);
	}

	public Integer getEntitytypeid() {
		return this.compositePrimaryKey.getEntitytypeid();
	}

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
	//--- DATABASE MAPPING : Value (varchar) 
	public void setValue(String value) {this.value = value;}
	public String getValue() {return this.value;}

	//--- DATABASE MAPPING : LastModifiedBy (nvarchar) 
	public void setLastmodifiedby(String lastmodifiedby) {this.lastmodifiedby = lastmodifiedby;}
	public String getLastmodifiedby() {return this.lastmodifiedby;}


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append("[" + compositePrimaryKey + "]"); 
		sb.append(value); 
		sb.append("|"); 
		sb.append(lastmodifiedby); 
        return sb.toString();
    }
}