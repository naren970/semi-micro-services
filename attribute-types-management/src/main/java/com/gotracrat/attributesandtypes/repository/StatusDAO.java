package com.gotracrat.attributesandtypes.repository;

import java.util.List;

import com.gotracrat.attributesandtypes.entity.Status;

public interface StatusDAO {

	List<Status> getAllStatus(int companyId, int entityTypeId);

}
