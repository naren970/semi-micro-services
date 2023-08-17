package com.gotracrat.managelocation.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Hateoas resource associated with Repair.
 * @author Telosys Prabhakar
 * @since 2018-11-30
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepairResource {

    private Integer repairid;  
    private String repairdescription;  
    private Integer companyid;  
    private Integer typeid;  
    private String  lastmodifiedby;
}
