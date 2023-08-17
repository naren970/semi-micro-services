package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.entity.*;
import com.gotracrat.managelocation.exception.ResourceNotFoundException;
import com.gotracrat.managelocation.repository.*;
import com.gotracrat.managelocation.resource.ItemResource;
import com.gotracrat.managelocation.resource.TransferLogResource;
import com.gotracrat.managelocation.utils.GoTracratConstants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransferLogServiceImpl implements TransferLogService {

    @Autowired
    private TransferLogRepository transferLogRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private ItemDAO itemDAOImpl;

    @Autowired
    private ItemServiceImpl itemServiceImpl;

    /**
     * This method is used for to get all Transfers
     *
     * @return List of TransferLog
     */
    @Override
    public List<TransferLog> getAllTransfers(Integer itemId) {
        List<TransferLog> transferLogs = transferLogRepository.findByItemIDOrderByTransferDateDesc(itemId);
        int size = transferLogs.size();
        for (int i = size - 1; i > -1; i--) {
            if (i == 0) {
                Date inServiceOn = itemRepository.getInserviceOn(itemId);
                Date dateNow = java.util.Calendar.getInstance().getTime();
                long daysInService = 0;
                if (inServiceOn != null) {
                    long inService = dateNow.getTime() - inServiceOn.getTime();
                    daysInService = TimeUnit.MILLISECONDS.toDays(inService);
                }
                transferLogs.get(i).setDaysinOldStatus((int) daysInService);
            } else {
                transferLogs.get(i).setDaysinOldStatus(transferLogs.get(i - 1).getDaysinOldStatus());
            }
        }
        return transferLogs;
    }

    /**
     * This method is used for to get TransferLog
     *
     * @return TransferLog
     */
    @Override
    public TransferLogResource getTransfer(Integer transferLogId) {
        TransferLogResource transferLogResource = new TransferLogResource();
        Optional<TransferLog> transferLogOp = transferLogRepository.findById(transferLogId);
        if (transferLogOp.isPresent()) {
            TransferLog transferLog = transferLogOp.get();
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ssZ");
            String dateAddedOn = dateFormat.format(transferLog.getTransferDate());
            BeanUtils.copyProperties(transferLog, transferLogResource);
            transferLogResource.setNewStatusName(transferLog.getNewStatus());
            transferLogResource.setTransfeDate(dateAddedOn);
            return transferLogResource;
        }
        throw new ResourceNotFoundException(GoTracratConstants.TRANSFER_LOG_NOT_FOUND + transferLogId);
    }

    /**
     * This method is used for to create TransferLog
     *
     * @return TransferLogResource
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public TransferLogResource createTransfer(TransferLogResource transferLogResource) {
        TransferLog transferLog = new TransferLog();
        String locationName = itemDAOImpl.getLocationName(transferLogResource.getToLocationID());
        transferLogResource.setToLocation(locationName);
        BeanUtils.copyProperties(transferLogResource, transferLog);
        String statusName = statusRepository.findStatusByStatusid(transferLogResource.getStatusID());
        transferLog.setNewStatus(statusName);
        ItemResource itemResource = itemServiceImpl.getItem(transferLogResource.getItemID());
        if (itemResource.getStatusid().equals(transferLogResource.getNewStatus())) {
            transferLog.setDaysinOldStatus(itemResource.getDaysinservice().intValue());
        } else {
            Date dateNow = java.util.Calendar.getInstance().getTime();
            long backTracked = dateNow.getTime() - transferLogResource.getTransferDate().getTime();
            long daysBackTracked = TimeUnit.MILLISECONDS.toDays(backTracked);
            transferLogResource.setDaysbacktracked(daysBackTracked);
            if (itemResource.getDaysinservice().intValue() != 0) {
                transferLog.setDaysinOldStatus(itemResource.getDaysinservice().intValue() - transferLogResource.getDaysbacktracked().intValue());
            } else {
                transferLog.setDaysinOldStatus(transferLogResource.getDaysbacktracked().intValue());
            }
        }
        transferLog = transferLogRepository.save(transferLog);
        Location location = new Location();
        location.setLocationid(transferLog.getToLocationID());
        Optional<Item> itemOp = itemRepository.findById(transferLog.getItemID());
        TransferLog finalTransferLog = transferLog;
        itemOp.ifPresent(item -> {
            item.setLocation(location);
            if (!itemResource.getStatusid().equals(transferLogResource.getNewStatus())) {
                item.setInserviceon(finalTransferLog.getTransferDate());
                item.setLastmodifiedby(transferLogResource.getTransferredBy());
            } else {
                item.setLastmodifiedby(transferLogResource.getTransferredBy());
            }
            Status status = new Status();
            status.setStatusid(transferLogResource.getStatusID());
            item.setStatus(status);
            itemRepository.saveAndFlush(item);
            saveJournal(finalTransferLog, item, transferLogResource);
        });
        BeanUtils.copyProperties(finalTransferLog, transferLogResource);
        return transferLogResource;
    }

    @Override
    public String delete(Integer transferLogId) {
        if(transferLogRepository.existsById(transferLogId)){
            transferLogRepository.deleteById(transferLogId);
            return GoTracratConstants.TRANSFER_LOG_ITEM_DELETED;
        }
        throw new ResourceNotFoundException(GoTracratConstants.INVALID_TRANSFER_LOG_ITEM_ID+transferLogId);
    }

    private void saveJournal(TransferLog created, Item item, TransferLogResource transferLogResource) {
        Journal journal = new Journal();
        journal.setEntityid(created.getItemID());
        journal.setEntitytypeid(2);
        journal.setEnteredon(created.getTransferDate());
        journal.setEffectiveon(created.getTransferDate());
        journal.setLocationid(created.getFromLocationID());
        journal.setLocationname(created.getFromLocation());
        journal.setJournaltypeid(7);
        journal.setEnteredby(transferLogResource.getTransferredBy());
        journal.setEntityname(item.getTag());
        journal.setCompanyid(transferLogResource.getCompanyID());
        journal.setEntry("Item " + item.getName() + " with Tag   " + item.getTag() +
                "  transferred from  " + created.getFromLocation() + "  to  " +
                created.getToLocation());
        journalRepository.save(journal);
    }
}
