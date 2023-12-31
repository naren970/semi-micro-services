/////

/*
 * Created on 2018-11-29 ( Date ISO 2018-11-29 - Time 19:27:59 )
 * Generated by Prabhakar
*/
package com.gotracrat.managelocation.service;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import com.gotracrat.managelocation.dto.RepairLogAttachmentDTO;
import com.gotracrat.managelocation.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gotracrat.managelocation.resource.RepairLogMappingResource;
import com.gotracrat.managelocation.resource.RepairLogResource;
import com.gotracrat.managelocation.entity.FailureType;
import com.gotracrat.managelocation.entity.Item;
import com.gotracrat.managelocation.entity.Journal;
import com.gotracrat.managelocation.entity.RepairLogMapping;
import com.gotracrat.managelocation.entity.Repairlog;
import com.gotracrat.managelocation.entity.UserLog;
import com.gotracrat.managelocation.entity.WarrantyType;
import com.gotracrat.managelocation.repository.FailureTypeRepository;
import com.gotracrat.managelocation.repository.ItemRepository;
import com.gotracrat.managelocation.repository.JournalRepository;
import com.gotracrat.managelocation.repository.RepairLogDAO;
import com.gotracrat.managelocation.repository.RepairLogMappingRepository;
import com.gotracrat.managelocation.repository.RepairlogRepository;
import com.gotracrat.managelocation.utils.GoTracratConstants;

/**
 * Service implementation for Repairlog.
 * 
 * @author Prabhakar
 */
@Service
public class RepairLogServiceImpl implements RepairLogService {

	@Autowired
	private RepairlogRepository repairlogRepository;

	@Autowired
	private RepairLogMappingRepository repairLogMappingRepository;

	@Autowired
	private RepairLogDAO repairLogDAO;

	@Autowired
	private FailureTypeRepository failuretypeRepository;

	@Autowired
	private JournalRepository journalRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public List<RepairLogResource> getAllItemRepairs(Integer companyid, Integer itemId, String Completed) {

		List<Repairlog> repairlogList;
		if (Completed.equalsIgnoreCase(GoTracratConstants.REPAIR_COMPLETED)) {
			repairlogList = repairlogRepository.findRepairLogByItemidAndIsActive(itemId,
					GoTracratConstants.REPAIR_COMPLETED_FLAG);
		} else {
			repairlogList = repairlogRepository.findRepairLogByItemidAndIsActive(itemId,
					GoTracratConstants.REPAIR_INCOMPLETED_FLAG);
		}
		List<RepairLogResource> repairlogResourceList = new ArrayList<>();
		if (!repairlogList.isEmpty()) {
			List<RepairLogAttachmentDTO> allAttachmentList = repairLogDAO.getAllAttachments(itemId);
			repairlogList.forEach(repairItem -> {
				RepairLogResource repairLogResource = new RepairLogResource();
				BeanUtils.copyProperties(repairItem, repairLogResource);
				repairLogResource.setAttachmentList(allAttachmentList.stream()
						.filter(a -> a.getEntityId().equals(repairLogResource.getRepairlogid()))
						.collect(Collectors.toList()));
				repairlogResourceList.add(repairLogResource);
			});

		}
		return repairlogResourceList;
	}

	@Override
	public RepairLogResource getItemRepair(Integer repairlogid) {
		Repairlog repairlog = repairlogRepository.findById(repairlogid).orElseThrow(() ->
				new ResourceNotFoundException(GoTracratConstants.ITEM_NOT_FOUND));
		RepairLogResource repairlogResource = new RepairLogResource();
		BeanUtils.copyProperties(repairlog, repairlogResource);
		List<RepairLogMappingResource> repairLogMappingResourceList = getSecondaryCauses(repairlogid);
		repairlogResource.setSecondaryTypeAndCauses(repairLogMappingResourceList);

		return repairlogResource;
	}

	private List<RepairLogMappingResource> getSecondaryCauses(Integer repairlogid) {
		List<RepairLogMappingResource> repairLogMappingResourceList = new ArrayList<>();
		List<RepairLogMapping> repairLogMappingList = repairLogMappingRepository
				.findByRepairLogIdAndIsActive(repairlogid, true);
		if (!repairLogMappingList.isEmpty()) {
			repairLogMappingList.forEach(repairLogMapping -> {
				RepairLogMappingResource repairLogMappingResource = new RepairLogMappingResource();
				BeanUtils.copyProperties(repairLogMapping, repairLogMappingResource);
				repairLogMappingResourceList.add(repairLogMappingResource);
			});
		}
		return repairLogMappingResourceList;

	}

	@Override
	public RepairLogResource getItemRepairForView(Integer repairlogid) {
		RepairLogResource repairlogResource = repairLogDAO.getRepairLog(repairlogid);
		List<RepairLogMappingResource> repairLogMappingResourceList = getSecondaryCauses(repairlogid);
		repairlogResource.setSecondaryTypeAndCauses(repairLogMappingResourceList);
		return repairlogResource;
	}

	@Override
	public String deleteItemRepair(Integer repairlogid, UserLog userLog) {

		List<RepairLogMapping> repairLogMappingList = repairLogMappingRepository
				.findByRepairLogIdAndIsActive(repairlogid, true);
		if (!repairLogMappingList.isEmpty()) {
			repairLogMappingRepository.deleteSecondaryFindingsAndCauses(repairlogid);
		}
		Optional<Repairlog> optionalRepailog = Optional.ofNullable(repairlogRepository.findByRepairLogId(repairlogid));
		if (optionalRepailog.isPresent()) {
			repairlogRepository.deleteRepair(repairlogid);
			return GoTracratConstants.REPAIR_LOG_DELETED;
		}
		throw new ResourceNotFoundException(GoTracratConstants.INVALID_REPAIR_LOG_ID + repairlogid);
	}

	@Override
	public RepairLogResource createItemRepair(RepairLogResource repairlogResource, UserLog userLog) {
		Repairlog repairlog = new Repairlog();
		BeanUtils.copyProperties(repairlogResource, repairlog);
		repairlog.setIsactive(repairlogResource.getIsactive());
		if (repairlogResource.getWarrantytypeid() != null && repairlogResource.getWarrantytypeid() != 0) {
			Item itemTemp = itemRepository.findById(repairlogResource.getItemid()).orElseThrow(() ->
					new ResourceNotFoundException(GoTracratConstants.ITEM_NOT_FOUND));
			if (repairlogResource.getWarrantyexpiration() != null) {
				itemTemp.setWarrantyexpiration(repairlogResource.getWarrantyexpiration());
			}
			WarrantyType warrantytype = new WarrantyType();
			warrantytype.setWarrantytypeid(repairlogResource.getWarrantytypeid());
			itemTemp.setWarrantytype(warrantytype);
			itemRepository.save(itemTemp);
		}
		Repairlog repairlog1 = repairlogRepository.save(repairlog);
		repairlogResource.setRepairlogid(repairlog1.getRepairlogid());
		if (!repairlogResource.getSecondaryTypeAndCauses().isEmpty()) {
			 addSecondaryFindingsAndCauses(repairlogResource);
		}
		Journal journal = new Journal();
		journal.setEntityid(repairlogResource.getItemid());
		journal.setEntitytypeid(2);
		journal.setEnteredon(repairlogResource.getDateinitiated());
		journal.setEffectiveon(repairlogResource.getDateinitiated());

		journal.setJournaltypeid(3);
		journal.setEnteredby(repairlogResource.getUserName());
		journal.setEntityname(repairlogResource.getTag());
		journal.setCompanyid(repairlogResource.getCompanyId());
		journal.setPonumber(repairlogResource.getPonumber());
		journal.setJobnumber(repairlogResource.getJobnumber());
		journal.setEntry("Repair added with Jobnumber: " + repairlogResource.getJobnumber() + " and PONumber: "
				+ repairlogResource.getPonumber());

		journalRepository.save(journal);
		return repairlogResource;
	}

	@Override
	public Boolean saveItemRepair(RepairLogResource repairlogResource, UserLog userLog) throws ParseException {
		Repairlog repairlog = new Repairlog();
		BeanUtils.copyProperties(repairlogResource, repairlog);
		final Integer pk = repairlogResource.getRepairlogid();
		if (repairlogResource.getWarrantytypeid() != null && repairlogResource.getWarrantytypeid() != 0) {
			Item itemTemp = itemRepository.findById(repairlogResource.getItemid()).orElseThrow(() ->
					new ResourceNotFoundException(GoTracratConstants.ITEM_NOT_FOUND));
			if (repairlogResource.getWarrantyexpiration() != null) {
				itemTemp.setWarrantyexpiration(repairlogResource.getWarrantyexpiration());
			}
			WarrantyType warrantytype = new WarrantyType();
			warrantytype.setWarrantytypeid(repairlogResource.getWarrantytypeid());
			itemTemp.setWarrantytype(warrantytype);
			itemRepository.save(itemTemp);
		}
		if (repairlogRepository.findById(pk).isPresent()) {
			Repairlog repairLog = repairlogRepository.save(repairlog);
			repairlogResource.setRepairlogid(repairLog.getRepairlogid());
			if (!repairlogResource.getSecondaryTypeAndCauses().isEmpty()) {
				editSecondaryFindingsAndCauses(repairlogResource);
			}
			Journal journal = new Journal();
			journal.setEntityid(repairlogResource.getItemid());
			journal.setEntitytypeid(2);
			journal.setEnteredon(repairlogResource.getDateinitiated());
			journal.setEffectiveon(repairlogResource.getDateinitiated());
			journal.setJournaltypeid(3);
			journal.setEnteredby(repairlogResource.getUserName());
			journal.setEntityname(repairlogResource.getTag());
			journal.setCompanyid(repairlogResource.getCompanyId());
			journal.setPonumber(repairlogResource.getPonumber());
			journal.setJobnumber(repairlogResource.getJobnumber());
			journal.setEntry("Repair updated with Jobnumber: " + repairlogResource.getJobnumber() + " and PONumber: "
					+ repairlogResource.getPonumber());
			 journalRepository.save(journal);
			return true;
		}
		return false;
	}

	private RepairLogResource editSecondaryFindingsAndCauses(RepairLogResource repairlogResource) {

		List<RepairLogMappingResource> secondaryCausesList = repairlogResource.getSecondaryTypeAndCauses();
		RepairLogMapping repairLogMapping = new RepairLogMapping();
		secondaryCausesList.forEach(repairLogMappingResource -> {
			if (!repairLogMappingRepository.findById(repairLogMappingResource.getRepairLogMappingId()).isPresent()) {
				repairLogMappingResource.setRepairLogId(repairlogResource.getRepairlogid());
			}
			BeanUtils.copyProperties(repairLogMappingResource, repairLogMapping);
			repairLogMappingRepository.save(repairLogMapping);
		});

		return repairlogResource;
	}

	private RepairLogResource addSecondaryFindingsAndCauses(RepairLogResource repairlogResource) {
		List<RepairLogMappingResource> secondaryCausesList = repairlogResource.getSecondaryTypeAndCauses();
		RepairLogMapping repairLogMapping = new RepairLogMapping();
		secondaryCausesList.forEach(failureType -> {
			failureType.setRepairLogId(repairlogResource.getRepairlogid());
			BeanUtils.copyProperties(failureType, repairLogMapping);
			repairLogMappingRepository.save(repairLogMapping);
		});
		return repairlogResource;
	}

	@Override
	public Boolean existItemRepair(RepairLogResource repairlogResource) {
		return repairlogRepository.existsById(repairlogResource.getRepairlogid());
	}

	@Override
	public Map<String, List<String>> getFailureTypeAndCauseForItemRepair(Integer companyid, Integer typeid) {
		List<FailureType> failureTypeList = failuretypeRepository.findByItemtypeid(typeid);
		Map<String, List<String>> fialureTypeCauseList = new LinkedHashMap<>();
		failureTypeList.forEach(failureType -> {
			List<String> failureCauseList = new ArrayList<>();
			if (failureType.getDescription() != null) {
				String[] failureCause = failureType.getCauses().split("  {2}");
				failureCauseList = Arrays.asList(failureCause);
			}

			List<String> failureCauseListTemp = new ArrayList<>();
			failureCauseList.forEach(failureCause ->
				failureCauseListTemp.add(failureCause.replace("\r", "")));
			String failureTypeWithId = failureType.getDescription();
			fialureTypeCauseList.put(failureTypeWithId, failureCauseListTemp);
		});
		return fialureTypeCauseList;
	}
}
