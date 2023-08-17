package com.gotracrat.managelocation.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemSearchRequestDTO {

	private Integer locationId;
	private Integer companyId;
	private String tag;
	private Integer statusId;
	private Integer typeId;
	private Boolean isOwnerAdmin;
	private String userId;
	
	public ItemSearchRequestDTO(Integer locationId, Integer companyId, String tag, Integer statusId, Integer typeId,
			Boolean isOwnerAdmin, String userId) {
		super();
		this.locationId = locationId;
		this.companyId = companyId;
		this.tag = tag;
		this.statusId = statusId;
		this.typeId = typeId;
		this.isOwnerAdmin = isOwnerAdmin;
		this.userId = userId;
	}
}
