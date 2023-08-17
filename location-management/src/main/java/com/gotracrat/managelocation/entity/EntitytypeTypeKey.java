/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:58 )
 * 
*/
package com.gotracrat.managelocation.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Composite primary key for entity "Entitytype-type" ( stored in table "EntityType-Type" )
 * @author prabhakar
 */
 @Embeddable
public class EntitytypeTypeKey implements Serializable {
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY KEY ATTRIBUTES 
    //----------------------------------------------------------------------
    @Column(name="EntityID", nullable=false)
    private Integer entityid ;
    @Column(name="EntityTypeID", nullable=false)
    private Integer entitytypeid ;
    @Column(name="TypeID", nullable=false)
    private Integer typeid ;

    //----------------------------------------------------------------------
    // CONSTRUCTORS
    //----------------------------------------------------------------------
    public EntitytypeTypeKey() {
        super();
    }

    public EntitytypeTypeKey(Integer entityid, Integer entitytypeid, Integer typeid) {
        super();
        this.entityid = entityid ;
        this.entitytypeid = entitytypeid ;
        this.typeid = typeid ;
    }
    
    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR KEY FIELDS
    //----------------------------------------------------------------------
	public void setEntityid(Integer value) {
        this.entityid = value;
    }
	public Integer getEntityid() {
        return this.entityid;
    }

	public void setEntitytypeid(Integer value) {
        this.entitytypeid = value;
    }
	public Integer getEntitytypeid() {
        return this.entitytypeid;
    }

	public void setTypeid(Integer value) {
        this.typeid = value;
    }
	public Integer getTypeid() {
        return this.typeid;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(entityid); 
		sb.append("|"); 
		sb.append(entitytypeid); 
		sb.append("|"); 
		sb.append(typeid); 
        return sb.toString();
    }
}
