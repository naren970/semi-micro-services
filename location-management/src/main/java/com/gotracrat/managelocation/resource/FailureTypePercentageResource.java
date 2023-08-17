package com.gotracrat.managelocation.resource;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * FailureType Percentage Resource.
 *
 * @author anudeep
 * @since 22-12-2021
 */
@Getter
@Setter
public class FailureTypePercentageResource {

    private String failureType;
    private String failureCause;
    private BigDecimal cost;
    private BigDecimal totalPercentage;
}
