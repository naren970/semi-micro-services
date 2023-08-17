/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:48:27 )
 *
*/
package com.gotracrat.managecompany.resource.resource;

/**
 * Hateoas resource associated with Attributelistitem.
 * @author prabhakar
 */
public class AttributelistitemResource {

    private Integer attributelistvaluesid;  
    private Integer attributenameid;  
    private String listitem;  
    private Integer listitembk;  

	// Constructor
	public AttributelistitemResource() {
		// Needed empty constructor for serialization
	}

	public Integer getAttributelistvaluesid() {
		return this.attributelistvaluesid;
	}
	public void setAttributelistvaluesid(Integer attributelistvaluesid) {
		this.attributelistvaluesid = attributelistvaluesid;
	}
	public Integer getAttributenameid() {
		return this.attributenameid;
	}
	public void setAttributenameid(Integer attributenameid) {
		this.attributenameid = attributenameid;
	}
	public String getListitem() {
		return this.listitem;
	}
	public void setListitem(String listitem) {
		this.listitem = listitem;
	}
	public Integer getListitembk() {
		return this.listitembk;
	}
	public void setListitembk(Integer listitembk) {
		this.listitembk = listitembk;
	}
}
