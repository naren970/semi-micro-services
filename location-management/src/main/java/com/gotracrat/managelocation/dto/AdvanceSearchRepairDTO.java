package com.gotracrat.managelocation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Hateoas resource associated with Repairlog.
 *
 * @author Prabhakar
 * @since 2018-11-29
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdvanceSearchRepairDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer repairLogId;
    private Integer itemId;
    private String typeName;
    private String tag;
    private String rfqNumber;
    private String poNumber;
    private String jobNumber;
    private String repairVendorName;
    private Integer rank;
    @JsonIgnore
    private Integer locationId;

}
