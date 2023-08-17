package com.gotracrat.attributesandtypes.repository;

import java.util.List;
import java.util.Map;

import com.gotracrat.attributesandtypes.controller.resource.AttributenameResource;

public interface TypeDAO {
	public Boolean delete(Integer typeId);

	public Map<String, Integer> getAttributesForSearchDisplay(Integer companyId);
}
