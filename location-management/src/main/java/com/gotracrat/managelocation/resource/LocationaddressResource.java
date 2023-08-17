/*
 * Created on 2018-07-15 ( Date ISO 2018-07-15 - Time 13:09:30 )
 * prabhakar
*/
package com.gotracrat.managelocation.resource;

/**
 * Hateoas resource associated with Locationaddress.
 * @author prabhakar
 */
public class LocationaddressResource  {

    private Integer locationaddressid;  
    private Integer locationid;  
    private String address1;  
    private String address2;  
    private String city;  
    private String state;  
    private String postalcode;  

	// Constructor
	public LocationaddressResource() {
		// Needed empty constructor for serialization
	}

	public Integer getLocationaddressid() {
		return this.locationaddressid;
	}
	public void setLocationaddressid(Integer locationaddressid) {
		this.locationaddressid = locationaddressid;
	}
	public Integer getLocationid() {
		return this.locationid;
	}
	public void setLocationid(Integer locationid) {
		this.locationid = locationid;
	}
	public String getAddress1() {
		return this.address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return this.address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return this.city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return this.state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalcode() {
		return this.postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
}
