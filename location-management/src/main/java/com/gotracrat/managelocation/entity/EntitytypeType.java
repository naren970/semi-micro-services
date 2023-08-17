/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:58 )
 * 
*/
package com.gotracrat.managelocation.entity;


import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Persistent class for Entitytype-type entity stored in table EntityType-Type.
 * This class is a "record entity" without JPA links.
 * @author prabhakar
 */
@Entity
@Table(name="EntityType-Type", schema="dbo" )
public class EntitytypeType implements Serializable {

	private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( EMBEDDED IN AN EXTERNAL CLASS )  
    //----------------------------------------------------------------------
	@EmbeddedId
    private EntitytypeTypeKey compositePrimaryKey ;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EntitytypeType() {
		super();
		this.compositePrimaryKey = new EntitytypeTypeKey();       
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
	public void setEntityid(Integer entityid) {
        this.compositePrimaryKey.setEntityid(entityid) ;
    }
	public Integer getEntityid() {
        return this.compositePrimaryKey.getEntityid() ;
    }
	public void setEntitytypeid(Integer entitytypeid) {
        this.compositePrimaryKey.setEntitytypeid(entitytypeid) ;
    }
	public Integer getEntitytypeid() {
        return this.compositePrimaryKey.getEntitytypeid() ;
    }
	public void setTypeid(Integer typeid) {
        this.compositePrimaryKey.setTypeid(typeid) ;
    }
	public Integer getTypeid() {
        return this.compositePrimaryKey.getTypeid() ;
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