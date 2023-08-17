/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:56 )
 * 
*/
package com.gotracrat.managecompany.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Composite primary key for entity "Attributetypesearchtype" ( stored in table "AttributeTypeSearchType" )
 * @author prabhakar
 */
 @Embeddable
public class AttributetypesearchtypeKey implements Serializable {
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY KEY ATTRIBUTES 
    //----------------------------------------------------------------------
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="AttributeTypeId", nullable=false)
	@JsonBackReference
    private Attributetype attributetypeid ;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="AttributeSearchTypeId", nullable=false)
	@JsonBackReference
    private Attributesearchtype attributesearchtypeid ;

    //----------------------------------------------------------------------
    // CONSTRUCTORS
    //----------------------------------------------------------------------
    public AttributetypesearchtypeKey() {
        super();
    }

    public AttributetypesearchtypeKey(Integer attributetypeid, Integer attributesearchtypeid) {
        super();
      /*  this.attributetypeid = attributetypeid ;
        this.attributesearchtypeid = attributesearchtypeid ;*/
        this.attributetypeid.setAttributetypeid(attributetypeid);
        this.attributesearchtypeid.setAttributesearchtypeid(attributesearchtypeid);
    }
    
    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR KEY FIELDS
    //----------------------------------------------------------------------
    public Attributetype getAttributetypeid() {
		return attributetypeid;
	}

	public void setAttributetypeid(Attributetype attributetypeid) {
		this.attributetypeid = attributetypeid;
	}

	public Attributesearchtype getAttributesearchtypeid() {
		return attributesearchtypeid;
	}

	public void setAttributesearchtypeid(Attributesearchtype attributesearchtypeid) {
		this.attributesearchtypeid = attributesearchtypeid;
	}


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(attributetypeid); 
		sb.append("|"); 
		sb.append(attributesearchtypeid); 
        return sb.toString();
    }
}
