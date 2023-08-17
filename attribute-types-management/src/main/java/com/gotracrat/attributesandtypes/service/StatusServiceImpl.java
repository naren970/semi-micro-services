/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:59 )
 * 
*/
package com.gotracrat.attributesandtypes.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.gotracrat.attributesandtypes.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gotracrat.attributesandtypes.controller.resource.StatusResource;
import com.gotracrat.attributesandtypes.entity.Company;
import com.gotracrat.attributesandtypes.entity.Status;
import com.gotracrat.attributesandtypes.entity.UserLog;
import com.gotracrat.attributesandtypes.repository.StatusDAO;
import com.gotracrat.attributesandtypes.repository.StatusRepository;
import com.gotracrat.attributesandtypes.utils.EntityTypeIDEnum;
import com.gotracrat.attributesandtypes.utils.GoTracratConstants;

/**
 * Service implementation for Status.
 * 
 * @author prabhakar
 */
@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepository statusRepository;


	@Autowired
	private StatusDAO statusDAO;


	@Override
	public List<StatusResource> getAllStatusByCompanyId(int companyId, String moduleType) {
		int entityTypeId = findEntityTypeId(companyId, moduleType);
		if (entityTypeId != 0) {
			List<Status> statusList = statusDAO.getAllStatus(companyId, entityTypeId);
			return this.buildStatusResource(statusList,companyId);
		}
		return Collections.emptyList();
	}

	@Override
	public Optional<Status> get(Integer statusId) {
		return statusRepository.findById(statusId);
	}

	@Override
	public boolean delete(Status status,UserLog userLog) {
		if (status != null) {
			statusRepository.deleteById(status.getStatusid());
			return true;
		}
		userLog.setCanNotInsert(true);
		return false;
	}

	@Override
	public StatusResource createStatus(StatusResource statusResource,UserLog userLog) {

		Status status = buildStatus(statusResource);
		if (status != null) {
			status = statusRepository.save(status);
			BeanUtils.copyProperties(status, statusResource);
		} else {
			userLog.setCanNotInsert(true);
			statusResource = null;
		}

		return statusResource;
	}

	@Override
	public boolean updateStatus(StatusResource statusResource,UserLog userLog) {
		final Integer pk = statusResource.getStatusid();
		if (statusRepository.findById(pk).isPresent()) {

			Status status = buildStatus(statusResource);
			if (status != null) {
				statusRepository.save(status);
				return true;
			}

		}
		userLog.setCanNotInsert(true);
		return false;
	}

	@Override
	public boolean exist(Status status) {
		return statusRepository.existsById(status.getStatusid());
	}

	private Status buildStatus(StatusResource statusResource) {
		Status status = new Status();
		BeanUtils.copyProperties(statusResource, status);
		if (statusResource.getModuleType() != null && 
				!statusResource.getModuleType().isEmpty() && statusResource.getCompanyid() != null
				&& statusResource.getCompanyid() != 0) {
			if (statusResource.getModuleType().equalsIgnoreCase(GoTracratConstants.COMPANY_MODULE_TYPE)) {
				status.setEntitytypeid(EntityTypeIDEnum.COMPANY_TYPE_ENTITYTYPE_ID.entityTypeID());
			} else if (statusResource.getModuleType().equalsIgnoreCase(GoTracratConstants.LOCATION_MODULE_TYPE)) {
				status.setEntitytypeid(EntityTypeIDEnum.LOCATION_TYPE_ENTITYTYPE_ID.entityTypeID());
			} else if (statusResource.getModuleType().equalsIgnoreCase(GoTracratConstants.ITEM_MODULE_TYPE)) {
				status.setEntitytypeid(EntityTypeIDEnum.ITEM_TYPE_ENTITYTYPE_ID.entityTypeID());
			} else {
				return null;
			}
			Company company = new Company();
			company.setCompanyid(statusResource.getCompanyid());
			status.setCompany(company);
		} else {
			return null;
		}

		return status;
	}

	private int findEntityTypeId(int companyId, String moduleType) {
		if (!moduleType.isEmpty() && companyId != 0) {
			if (moduleType.equalsIgnoreCase(GoTracratConstants.COMPANY_MODULE_TYPE)) {
				return EntityTypeIDEnum.COMPANY_TYPE_ENTITYTYPE_ID.entityTypeID();
			} else if (moduleType.equalsIgnoreCase(GoTracratConstants.LOCATION_MODULE_TYPE)) {
				return EntityTypeIDEnum.LOCATION_TYPE_ENTITYTYPE_ID.entityTypeID();
			} else if (moduleType.equalsIgnoreCase(GoTracratConstants.ITEM_MODULE_TYPE)) {
				return EntityTypeIDEnum.ITEM_TYPE_ENTITYTYPE_ID.entityTypeID();
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	private List<StatusResource> buildStatusResource(List<Status> statusList, int companyId) {
		final List<StatusResource> statusResourceList = new ArrayList<>();
		if (statusList != null && !statusList.isEmpty()) {
			statusList.forEach(status -> {
				StatusResource statusResource = new StatusResource();
				BeanUtils.copyProperties(status, statusResource);
				statusResource.setCompanyid(companyId);
				statusResourceList.add(statusResource);
			});
		}
		return statusResourceList;
	}

	@Override
	public  List<StatusResource> getAllItemStatusTablet(Integer companyID) {
		 List<Status> statusList=statusRepository.findByCompanyCompanyidAndEntitytypeidAndStatusIn(companyID,EntityTypeIDEnum.ITEM_TYPE_ENTITYTYPE_ID.entityTypeID(), GoTracratConstants.STATUSLIST);
		 statusList.sort(Comparator.comparing(Status::getStatus).reversed());
			return this.buildStatusResource(statusList,companyID);
		
	}

}
