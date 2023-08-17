package com.gotracrat.attributesandtypes.controller.resource;

import javax.xml.bind.annotation.XmlAttribute;

public class AttributeChangeLog {

	@XmlAttribute(name = "Name")
	private String Name;
	@XmlAttribute(name = "Value")
	private String Value;
	@XmlAttribute(name = "Previous_Value")
	private String Previous_Value;
	
	

	public AttributeChangeLog() {
		super();
	}



	public AttributeChangeLog(String name, String value, String previous_Value) {
		super();
		setName(name);
		setValue(value);
		setPrevious_Value(previous_Value);
	}



	public String getPrevious_Value() {
		return Previous_Value;
	}



	public void setPrevious_Value(String previous_Value) {
		Previous_Value = previous_Value;
	}



	public String getName() {
		return Name;
	}



	public void setName(String name) {
		Name = name;
	}



	public String getValue() {
		return Value;
	}



	public void setValue(String value) {
		Value = value;
	}

}
