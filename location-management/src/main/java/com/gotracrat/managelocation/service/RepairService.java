package com.gotracrat.managelocation.service;

import java.util.List;

import com.gotracrat.managelocation.resource.RepairResource;

/**
 * Service interface for Repair.
 *
 * @author Telosys Prabhakar
 * @since 2018-11-30
 */
public interface RepairService {

    /**
     * Recover an repair following an ids.
     *
     * @return the repair
     */
    List<RepairResource> getAllRepairItems(Integer companyId, Integer typeId);

    /**
     * Recover an repair following an id.
     *
     * @param repairId the given id
     * @return the repair
     */
    RepairResource get(Integer repairId);

    /**
     * Perform an repair deletion.
     *
     * @param repairId the given id
     * @return state of deletion (true if ok otherwise false)
     */
    String delete(Integer repairId);

    /**
     * Perform an repair creation.
     *
     * @param repairResource to create
     * @return created repair
     */
    RepairResource create(RepairResource repairResource);

    /**
     * Perform an repair update.
     *
     * @param repairResource to update
     * @return state of update (true if ok otherwise false)
     */
    void update(RepairResource repairResource);
}
