/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:57 )
 * 
*/
package com.gotracrat.managelocation.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Persistent class for Entitytype entity stored in table EntityType.
 * This class is a "record entity" without JPA links.
 * @author prabhakar
 */
@Entity
@Table(name="EntityType", schema="dbo" )
public class Entitytype implements Serializable {

	private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="EntityTypeID", nullable=false)
    private Integer entitytypeid ; 
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="Name", nullable=false, length=50)
    private String name ;     @Column(name="Description", length=2000)
    private String description ; 

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Entitytype() {
		super();
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
	public void setEntitytypeid(Integer entitytypeid) {
        this.entitytypeid = entitytypeid ;
    }
	public Integer getEntitytypeid() {
        return this.entitytypeid;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
	//--- DATABASE MAPPING : Name (varchar) 
	public void setName(String name) {this.name = name;}
	public String getName() {return this.name;}

	//--- DATABASE MAPPING : Description (varchar) 
	public void setDescription(String description) {this.description = description;}
	public String getDescription() {return this.description;}


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(entitytypeid); 
		sb.append(name); 
		sb.append("|"); 
		sb.append(description); 
        return sb.toString();
    }
}