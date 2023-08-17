package com.gotracrat.managelocation.utils;

public enum ModulesEnum {

    COMPANY("COMPANY"),
    LOCATION("LOCATION"),
    ITEM("ITEM"),
    REPAIRLOG("ITEMREPAIR"),
    STATUS("STATUS"),
    TYPE("TYPE"),
    ATTACHMENT("ATTACHMENT"),
    ATTRIBUTENAME("ATTRIBUTENAME"),
    FAILURETYPE("FAILURETYPE"),
    JOURNAL("JOURNAL"),
    WARRANTYTYPE("WARRANTYTYPE"),
    ITEMREPAIRITEM("ITEMREPAIRITEM"),
    TEMPLATE("TEMPLATE"),
    USER("USER"),
    USERSECURITY("USERSECURITY");

    private String module;

    ModulesEnum(String module) {
        this.module = module;
    }

    public String getModule() {
        return module;
    }


}
