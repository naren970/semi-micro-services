package com.gotracrat.managecompany.util;

public enum JournalTypeIDEnum {

	NOTES_JOURNALTYPE_ID(4);

	private int typeID;

	JournalTypeIDEnum(int typeID) {
		this.typeID = typeID;
	}

	public int typeID() {
		return typeID;
	}
}
