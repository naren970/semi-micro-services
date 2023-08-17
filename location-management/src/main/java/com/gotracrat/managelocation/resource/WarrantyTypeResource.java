package com.gotracrat.managelocation.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Hateoas resource associated with WarrantyType.
 * @author prabhakar
 * @since 13-08-2018
 */

@Getter
@Setter
@AllArgsConstructor
public class WarrantyTypeResource {

    private Integer warrantytypeid;
    private Integer companyid;  
    private String warrantytype; 
    private String userName;

	public WarrantyTypeResource() {
		// Needed empty constructor for serialization
	}
}
