package com.gotracrat.managelocation.service;

import java.util.List;
import java.util.Map;

import com.gotracrat.managelocation.entity.FailureType;
import com.gotracrat.managelocation.entity.UserLog;

/**
 * Rest controller
 * @author prabhakar
 * @since 2018-11-29
 */
public interface FailureTypeService {

	FailureType createFailureType(FailureType failureType);

	void updateFailureType(FailureType failuretype);

	Map<String, List<String>> getFailureTypeAndCause(Integer companyId, Integer typeId);

	String deleteFailureType(Integer failureTypeId, UserLog userLog);

}
