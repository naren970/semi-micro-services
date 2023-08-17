package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.entity.Repair;
import com.gotracrat.managelocation.exception.ResourceNotFoundException;
import com.gotracrat.managelocation.repository.RepairRepository;
import com.gotracrat.managelocation.resource.RepairResource;
import com.gotracrat.managelocation.utils.GoTracratConstants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation for Repair.
 * 
 * @author Telosys Prabhakar
 * @since 2018-11-30
 */
@Service
public class RepairServiceImpl implements RepairService {

	@Autowired
	private RepairRepository repairRepository;

	/**
	 * This method is used for get all item repair Items
	 * @return List of Repair Resources
	 */
	@Override
	public List<RepairResource> getAllRepairItems(Integer companyId, Integer typeId){
		List<Repair> repairs =repairRepository.findByCompanyidAndTypeid(companyId,typeId);
		List<RepairResource> repairResources = new ArrayList<>();
		repairs.forEach(repair -> {
			RepairResource repairResource = new RepairResource();
			BeanUtils.copyProperties(repair, repairResource);
			repairResources.add(repairResource);
		});
		return repairResources;
	}

	/**
	 * This method is used for Get item repair Item
	 * @return  Repair Resource
	 */
	@Override
	public RepairResource get(Integer repairId) {
		if(repairRepository.existsById(repairId)) {
			return  buildRepairResource(repairRepository.findById(repairId).orElseThrow(() ->
					new ResourceNotFoundException(GoTracratConstants.INVALID_ID)));
		}
		throw new ResourceNotFoundException(GoTracratConstants.INVALID_ID + repairId);
	}

	/**
	 * This method is used for Create an item repair Item
	 * @return  Repair Resource
	 */
	@Override
	public RepairResource create(RepairResource repairResource) {
		return buildRepairResource(repairRepository.save(buildRepair(repairResource)));
	}

	/**
	 * This method is used for Update an item repair Item
	 */
	@Override
	public void update(RepairResource repairResource) {
		if (repairRepository.findById(repairResource.getRepairid()).isPresent()) {
			repairRepository.save(buildRepair(repairResource));
			return;
		}
		throw new ResourceNotFoundException(GoTracratConstants.INVALID_ID + repairResource.getRepairid());
	}

	/**
	 * This method is used for Delete an item repair Item
	 */
	@Override
	public String delete(Integer repairId) {
		if (repairRepository.findById(repairId).isPresent()) {
			repairRepository.deleteById(repairId);
			return GoTracratConstants.ITEM_REPAIR_ITEM_DELETED;
		}
		throw new ResourceNotFoundException(GoTracratConstants.INVALID_ITEM_REPAIR_ITEM_ID + repairId);
	}

	private Repair buildRepair(RepairResource repairResource) {
		Repair repair = new Repair();
		BeanUtils.copyProperties(repairResource, repair);
		return repair;
	}

	private RepairResource buildRepairResource(Repair repair) {
		RepairResource repairResource = new RepairResource();
		BeanUtils.copyProperties(repair, repairResource);
		return repairResource;
	}


}
