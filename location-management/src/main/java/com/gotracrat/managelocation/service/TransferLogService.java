package com.gotracrat.managelocation.service;

import java.util.List;

import com.gotracrat.managelocation.resource.TransferLogResource;
import com.gotracrat.managelocation.entity.TransferLog;

public interface TransferLogService {
	List<TransferLog> getAllTransfers(Integer itemId);

	TransferLogResource getTransfer(Integer transferLogId);

	TransferLogResource createTransfer(TransferLogResource transferLogResource);

	String delete(Integer transferLogId);
}
