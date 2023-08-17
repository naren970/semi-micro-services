/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:56 )
 * 
*/
package com.gotracrat.attributesandtypes.entity;


import java.io.Serializable;
import javax.persistence.*;

/**
 * Persistent class for Attributetypesearchtype entity stored in table AttributeTypeSearchType.
 * This class is a "record entity" without JPA links.
 * @author prabhakar
 */
@Entity
@Table(name="AttributeTypeSearchType", schema="dbo" )
public class Attributetypesearchtype implements Serializable {

	private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( EMBEDDED IN AN EXTERNAL CLASS )  
    //----------------------------------------------------------------------
	@EmbeddedId
    private AttributetypesearchtypeKey compositePrimaryKey ;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Attributetypesearchtype() {
		super();
		this.compositePrimaryKey = new AttributetypesearchtypeKey();       
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
	public void setAttributetypeid(Attributetype attributetypeid) {
        this.compositePrimaryKey.setAttributetypeid(attributetypeid) ;
    }
	public Attributetype getAttributetypeid() {
        return this.compositePrimaryKey.getAttributetypeid() ;
    }
	public void setAttributesearchtypeid(Attributesearchtype attributesearchtypeid) {
        this.compositePrimaryKey.setAttributesearchtypeid(attributesearchtypeid) ;
    }
	public Attributesearchtype getAttributesearchtypeid() {
        return this.compositePrimaryKey.getAttributesearchtypeid() ;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append("[" + compositePrimaryKey + "]"); 
        return sb.toString();
    }
}