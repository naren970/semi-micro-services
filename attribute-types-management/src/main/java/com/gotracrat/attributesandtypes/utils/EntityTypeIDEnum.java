package com.gotracrat.attributesandtypes.utils;

public enum EntityTypeIDEnum {
	ATTACHMENT_ENTITYTYPE_ID(9),
	NOTES_ENTITYTYPE_ID(1),
	STATUS_ENTITYTYPE_ID(1),
	COMPANY_TYPE_ENTITYTYPE_ID(1),
	ITEM_TYPE_ENTITYTYPE_ID(2),
	ITEM_TYPE_NOTE_ENTITYTYPE_ID(10),
	LOCATION_TYPE_ENTITYTYPE_ID(3),
	USER_TYPE_ENTITYTYPE_ID(9),
	ITEM_REPAIR_ENTITYTYPE_ID(12);
	

	private int entityTypeID;

	EntityTypeIDEnum(int entityTypeID) {
		this.entityTypeID = entityTypeID;
	}

	public int entityTypeID() {
		return entityTypeID;
	}
}
