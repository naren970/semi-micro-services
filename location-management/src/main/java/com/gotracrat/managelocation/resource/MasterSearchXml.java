package com.gotracrat.managelocation.resource;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "Attributes")
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class MasterSearchXml {

	@XmlElement(name = "AttributeName")
	private List<MasterSearchAttributesXml> attributesList;
	
}
