package com.gotracrat.managelocation.resource;

import com.gotracrat.managelocation.dto.ServicesDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemServicesResource {
	
	private List<ServicesDTO> completedServices;
	private List<ServicesDTO> inCompletedServices;
}
