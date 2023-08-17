package com.gotracrat.attributesandtypes.repository;

import java.util.List;

import com.gotracrat.attributesandtypes.controller.resource.AttributenameResource;

public interface AttributeDAO {
 public List<AttributenameResource>	updateAttributeOrder(List<AttributenameResource> attributenamelist);

public void delete(Integer attributenameid);

List<Integer> getTypeIdsByCompanyId(Integer companyId);
}
