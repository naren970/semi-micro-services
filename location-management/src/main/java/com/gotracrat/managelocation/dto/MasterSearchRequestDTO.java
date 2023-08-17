package com.gotracrat.managelocation.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MasterSearchRequestDTO {
	
	@NotNull(message="tagNumber must not be null")
	private String tag;
	private String name;
	private String locationName;
	private String typeName;
	private String statusName;
	
	public MasterSearchRequestDTO(@NotNull(message = "tagNumber must not be null") String tag) {
		super();
		this.tag = tag;
	}

} 
