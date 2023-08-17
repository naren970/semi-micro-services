package com.gotracrat.managelocation.resource;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;


@XmlRootElement(name = "Attributes")
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class AttributesXml {

	@XmlElement(name = "AttributeName")
	private List<AttributeNameXml> attributesList;
	
}
