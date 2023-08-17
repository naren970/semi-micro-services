package com.gotracrat.managelocation.resource;

public class AttributeChangeLog {

	private String Name;
	private String Value;
	private String Previous_Value;
	
	

	public AttributeChangeLog() {
		super();
	}



	public AttributeChangeLog(String name, String value, String previous_Value) {
		super();
		Name = name;
		Value = value;
		Previous_Value = previous_Value;
	}

}
