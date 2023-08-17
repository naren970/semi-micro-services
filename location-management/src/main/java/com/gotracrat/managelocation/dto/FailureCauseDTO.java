package com.gotracrat.managelocation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class    FailureCauseDTO {

    private Integer companyId;
    private Integer locationId;
    private String failureType;
    private String failureCause;
    private String isOwnerAdmin;
    private String isByRepairCost;
    private String userId;
    private String startDate;
    private String endDate;
    private String timeFrame;
    private Integer year;
    private String repairFlag;
    private Integer typeId;
}
