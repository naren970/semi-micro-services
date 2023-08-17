package com.gotracrat.attributesandtypes.utils;

public enum ModulesEnum {

    COMPANY("COMPANY"),
    LOCATION("LOCATION"),
    ITEM("ITEM"),
    REPAIRLOG("REPAIRLOG"),
    STATUS("STATUS"),
    TYPE("TYPE"),
    ATTACHMENT("ATTACHMENT"),
    ATTRIBUTENAME("ATTRIBUTENAME"),
    FAILURETYPE("FAILURETYPE"),
    JOURNAL("JOURNAL"),
    WARRANTYTYPE("WARRANTYTYPE"),
    REPAIR("REPAIR"),
    TEMPLATE("TEMPLATE"),
    USER("USER"),
    USERSECURITY("USERSECURITY"),
	NOTES("NOTES");

    private String module;

    ModulesEnum(String module) {
        this.module = module;
    }

    public String getModule() {
        return module;
    }


}
