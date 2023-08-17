/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:56 )
 * 
*/
package com.gotracrat.attributesandtypes.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Persistent class for Attributetype entity stored in table AttributeType.
 * This class is a "record entity" without JPA links.
 * @author prabhakar
 */
@Entity
@Table(name="AttributeType", schema="dbo" )
public class Attributetype implements Serializable {

	private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="AttributeTypeID", nullable=false)
    private Integer attributetypeid ; 
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="TypeName", nullable=false, length=255)
    private String typename ;     @Column(name="Description")
    private String description ; 

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Attributetype() {
		super();
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
	public void setAttributetypeid(Integer attributetypeid) {
        this.attributetypeid = attributetypeid ;
    }
	public Integer getAttributetypeid() {
        return this.attributetypeid;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
	//--- DATABASE MAPPING : TypeName (varchar) 
	public void setTypename(String typename) {this.typename = typename;}
	public String getTypename() {return this.typename;}

	//--- DATABASE MAPPING : Description (nvarchar) 
	public void setDescription(String description) {this.description = description;}
	public String getDescription() {return this.description;}


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(attributetypeid); 
		sb.append(typename); 
		sb.append("|"); 
		sb.append(description); 
        return sb.toString();
    }
}