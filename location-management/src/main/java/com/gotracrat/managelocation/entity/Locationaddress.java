/*
 * Created on 2018-07-15 ( Date ISO 2018-07-15 - Time 13:09:30 )
 * prabhakar
*/
package com.gotracrat.managelocation.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Persistent class for Locationaddress entity stored in table LocationAddress.
 * This class is a "record entity" without JPA links.
 * @author prabhakar
 */
@Entity
@Table(name="LocationAddress", schema="dbo" )
public class Locationaddress implements Serializable {

	private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="LocationAddressID", nullable=false)
    private Integer locationaddressid ; 
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="LocationID", nullable=false)
    private Integer locationid ;     @Column(name="Address1", length=100)
    private String address1 ;     @Column(name="Address2", length=100)
    private String address2 ;     @Column(name="City", length=50)
    private String city ;     @Column(name="State", length=50)
    private String state ;     @Column(name="PostalCode", length=10)
    private String postalcode ; 

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Locationaddress() {
		super();
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
	public void setLocationaddressid(Integer locationaddressid) {
        this.locationaddressid = locationaddressid ;
    }
	public Integer getLocationaddressid() {
        return this.locationaddressid;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
	//--- DATABASE MAPPING : LocationID (int) 
	public void setLocationid(Integer locationid) {this.locationid = locationid;}
	public Integer getLocationid() {return this.locationid;}

	//--- DATABASE MAPPING : Address1 (varchar) 
	public void setAddress1(String address1) {this.address1 = address1;}
	public String getAddress1() {return this.address1;}

	//--- DATABASE MAPPING : Address2 (varchar) 
	public void setAddress2(String address2) {this.address2 = address2;}
	public String getAddress2() {return this.address2;}

	//--- DATABASE MAPPING : City (varchar) 
	public void setCity(String city) {this.city = city;}
	public String getCity() {return this.city;}

	//--- DATABASE MAPPING : State (char) 
	public void setState(String state) {this.state = state;}
	public String getState() {return this.state;}

	//--- DATABASE MAPPING : PostalCode (varchar) 
	public void setPostalcode(String postalcode) {this.postalcode = postalcode;}
	public String getPostalcode() {return this.postalcode;}


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(locationaddressid); 
		sb.append(locationid); 
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
        return sb.toString();
    }
}
