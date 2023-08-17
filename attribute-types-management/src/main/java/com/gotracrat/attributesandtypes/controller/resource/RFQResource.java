package com.gotracrat.attributesandtypes.controller.resource;

import java.io.Serializable;

public class RFQResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String extraTag;
	private String RFQs;
	private String RFQnumber;
	private int rfqID;

	public String getExtraTag() {
		return extraTag;
	}

	public void setExtraTag(String extraTag) {
		this.extraTag = extraTag;
	}

	public String getRFQs() {
		return RFQs;
	}

	public void setRFQs(String rFQs) {
		RFQs = rFQs;
	}

	public String getRFQnumber() {
		return RFQnumber;
	}

	public void setRFQnumber(String rFQnumber) {
		RFQnumber = rFQnumber;
	}

	public int getRfqID() {
		return rfqID;
	}

	public void setRfqID(int rfqID) {
		this.rfqID = rfqID;
	}

}
