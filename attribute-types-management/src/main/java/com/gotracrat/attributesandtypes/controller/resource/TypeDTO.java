package com.gotracrat.attributesandtypes.controller.resource;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/*
 *DTO for Manage types
 *
 *@author Sanjay
 */
@Setter
@Getter
public class TypeDTO {
    private Integer typeid;
    private String name;
    private String description;
    private BigDecimal hostingfee;


    public TypeDTO(Integer typeid, String name,String description,BigDecimal hostingfee) {
        super();
        this.typeid = typeid;
        this.name = name;
        this.description = description;
        this.hostingfee = hostingfee;
    }
}




