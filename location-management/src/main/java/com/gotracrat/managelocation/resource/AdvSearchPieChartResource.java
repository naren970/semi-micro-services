package com.gotracrat.managelocation.resource;

import lombok.Data;

import java.util.List;

@Data
public class AdvSearchPieChartResource {

    private Integer companyId;
    private String failureType;
    private String failureCause;
    private String isOwnerAdmin;
    private String isByRepairCost;
    private String userId;
    private String startDate;
    private String endDate;
    private List<Integer> itemIds;
}
