package com.gotracrat.managelocation.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AttributeNameXml {

	@XmlElement(name = "AttributeNameID")
	private String attributeNameID;
	@XmlElement(name = "Name")
	private String name;
	@XmlElement(name = "Value")
	private String value;
}
