package com.gotracrat.managecompany.feignservice;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gotracrat.managecompany.resource.resource.TypeResource;

@FeignClient(name = "gotracratmanageattributeandtypes-service",fallback = RemoteCallServiceImpl.class)
public interface RemoteCallService {

	@RequestMapping(method = RequestMethod.GET, value = "api/type/getAllTypeHierarchy/{moduletype}/{companyid}")
	public List<TypeResource> getAllTypeHierarchy(@PathVariable("companyid") Integer companyid,
			@PathVariable("moduletype") String moduleType);

}
