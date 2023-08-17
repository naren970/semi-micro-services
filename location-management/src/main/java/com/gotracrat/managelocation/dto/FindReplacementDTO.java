package com.gotracrat.managelocation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindReplacementDTO {

	private String AttributeName;
	private String AttributeValue;
	
	
	public FindReplacementDTO(String attributeName, String attributeValue) {
		super();
		AttributeName = attributeName;
		AttributeValue = attributeValue;
	}


	public FindReplacementDTO() {
		super();
	}
}
