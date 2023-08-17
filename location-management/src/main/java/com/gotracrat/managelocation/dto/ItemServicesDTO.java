package com.gotracrat.managelocation.dto;

import java.util.List;

import com.gotracrat.managelocation.entity.ItemService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemServicesDTO {
	private List<ItemService> completedServices;
	private List<ItemService> inCompletedServices;
}
