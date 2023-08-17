/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:58 )
 * 
*/
package com.gotracrat.attributesandtypes.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Persistent class for Journaltype entity stored in table JournalType.
 * This class is a "record entity" without JPA links.
 * @author prabhakar
 */
@Entity
@Table(name="JournalType", schema="dbo" )
public class Journaltype implements Serializable {

	private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="JournalTypeID", nullable=false)
    private Integer journaltypeid ; 
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="Description", length=255)
    private String description ;     @Column(name="LookupCode", nullable=false, length=50)
    private String lookupcode ; 

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Journaltype() {
		super();
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
	public void setJournaltypeid(Integer journaltypeid) {
        this.journaltypeid = journaltypeid ;
    }
	public Integer getJournaltypeid() {
        return this.journaltypeid;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
	//--- DATABASE MAPPING : Description (varchar) 
	public void setDescription(String description) {this.description = description;}
	public String getDescription() {return this.description;}

	//--- DATABASE MAPPING : LookupCode (varchar) 
	public void setLookupcode(String lookupcode) {this.lookupcode = lookupcode;}
	public String getLookupcode() {return this.lookupcode;}


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(journaltypeid); 
		sb.append(description); 
		sb.append("|"); 
		sb.append(lookupcode); 
        return sb.toString();
    }
}