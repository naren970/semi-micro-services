package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.entity.FailureType;
import com.gotracrat.managelocation.entity.UserLog;
import com.gotracrat.managelocation.exception.ResourceNotFoundException;
import com.gotracrat.managelocation.repository.FailureTypeRepository;
import com.gotracrat.managelocation.utils.GoTracratConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Rest controller
 * @author prabhakar
 * @since 2018-11-29
 */
@Service
public class FailureTypeServiceImpl implements FailureTypeService {


	@Autowired
	private FailureTypeRepository failureTypeRepository;

	/**
	 * This method is used for to create a FailureType
	 *
	 * @return FailureType
	 */
	@Override
	public FailureType createFailureType(FailureType failureType) {
		return failureTypeRepository.save(failureType);
	}

	/**
	 * This method is used for to update a FailureType
	 */
	@Override
	public void updateFailureType(FailureType failuretype) {
		if (failureTypeRepository.existsById(failuretype.getFailuretypeid())) {
			failureTypeRepository.save(failuretype);
			return;
		}
		throw new ResourceNotFoundException(GoTracratConstants.INVALID_WARRANTY_TYPE_ID + failuretype.getFailuretypeid());
	}

	/**
	 * This method is used for to get failureType and causes
	 *
	 * @return Map<String, List<String>>
	 */
	@Override
	public Map<String, List<String>> getFailureTypeAndCause(Integer companyId, Integer typeId) {
		List<FailureType> failureTypes = failureTypeRepository.findByItemtypeid(typeId);
		Map<String, List<String>> failureTypeCauses = new LinkedHashMap<>();
		failureTypes.forEach(failureType -> {
			List<String> failureCauses = new ArrayList<>();
			if (failureType.getDescription() != null) {
				String[] failureCause = failureType.getCauses().split("  {2}");
				failureCauses = Arrays.asList(failureCause);
			}
			String failureTypeWithId = failureType.getDescription().concat("_")
					.concat(failureType.getFailuretypeid().toString());
			failureTypeCauses.put(failureTypeWithId, failureCauses);
		});
		return failureTypeCauses;
	}

	/**
	 * This method is used for to delete failureType
	 */
	@Override
	public String deleteFailureType(Integer failureTypeId, UserLog userLog) {
		if (failureTypeRepository.existsById(failureTypeId)) {
			failureTypeRepository.deleteById(failureTypeId);
			return GoTracratConstants.FAILURE_TYPE_DELETED;
		}
		throw new ResourceNotFoundException(GoTracratConstants.INVALID_FAILURE_TYPE_ID + failureTypeId);
	}
}
