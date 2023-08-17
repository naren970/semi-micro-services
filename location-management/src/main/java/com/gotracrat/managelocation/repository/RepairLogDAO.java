package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.dto.RepairLogAttachmentDTO;
import com.gotracrat.managelocation.resource.RepairLogResource;

import java.util.List;

public interface RepairLogDAO {

	public List<RepairLogAttachmentDTO> getAllAttachments(Integer itemId);

	public RepairLogResource getRepairLog(Integer repairlogid);

}