package com.gotracrat.managelocation.resource;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class MasterSearchAttributesXml {

	@XmlElement(name = "Name")
	private String attributeName;
	@XmlElement(name = "Value")
	private String value;
}
