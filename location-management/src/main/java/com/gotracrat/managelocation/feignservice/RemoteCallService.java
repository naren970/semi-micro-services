/*
package com.gotracrat.managelocation.feignservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gotracrat.managelocation.resource.AttributeValueResource;

@FeignClient(name = "attribute-service")
public interface RemoteCallService {

	@RequestMapping(method = RequestMethod.GET, value = "/api/remote/getAllAttributesByEntityId/{entityId}/{entityTypeId}")
	public List<AttributeValueResource> getAllAttributesByEntityId(@PathVariable("entityId") Integer entityId,
                                                                   @PathVariable("entityTypeId") Integer entityTypeId);

}
*/
