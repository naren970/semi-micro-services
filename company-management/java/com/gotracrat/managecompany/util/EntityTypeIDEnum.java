package com.gotracrat.managecompany.util;

public enum EntityTypeIDEnum {
	ATTACHMENT_ENTITYTYPE_ID(9),
	NOTES_ENTITYTYPE_ID(1),
	STATUS_ENTITYTYPE_ID(1),
	COMPANY_TYPE_ENTITYTYPE_ID(1),
	USER_TYPE_ENTITYTYPE_ID(9);

	private int entityTypeID;

	EntityTypeIDEnum(int entityTypeID) {
		this.entityTypeID = entityTypeID;
	}

	public int entityTypeID() {
		return entityTypeID;
	}
}
