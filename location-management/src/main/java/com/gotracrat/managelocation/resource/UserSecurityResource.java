package com.gotracrat.managelocation.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Hateoas resource associated with Usersecurity.
 * @author prabhakar
 * @since 2018-10-27
 */
@Getter
@Setter
@AllArgsConstructor
public class UserSecurityResource {

    private String userid;  
    private String roleid;  
    private Integer companyid;  
    private Integer locationid;  
    private String lastmodifiedby;

	public UserSecurityResource() {

	}
}
