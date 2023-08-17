package com.gotracrat.managecompany.feignservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gotracrat.managecompany.resource.resource.TypeResource;

@Component
public class RemoteCallServiceImpl implements RemoteCallService {

	@Override
	public List<TypeResource> getAllTypeHierarchy(Integer companyid, String moduleType) {
		return new ArrayList<TypeResource>();
	}

}
