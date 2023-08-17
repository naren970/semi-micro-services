package com.gotracrat.managelocation.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/*
 * DTO associated with InserviceVsSpare report API response .
 * @author manikanta
 * @since 2020-01-12
 */
@Getter
@Setter
public class InserviceVsSpareResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<InServiceVsSpareDTO> inServiceAndSpareMotors;
	
	private List<InServiceMotorsDTO>  unmatchedServiceMotors;

	private List<InServiceMotorsDTO>  unmatchedSpareMotors;

}
