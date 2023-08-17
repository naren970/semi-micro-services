package com.gotracrat.managelocation.utils;

public enum JournalTypeIDEnum {

	NOTES_JOURNALTYPE_ID(4),
	CHANGELOG_JOURNALTYPE_ID(3);

	private int typeID;

	JournalTypeIDEnum(int typeID) {
		this.typeID = typeID;
	}

	public int typeID() {
		return typeID;
	}
}
