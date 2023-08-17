package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.dto.ItemServicesDTO;
import com.gotracrat.managelocation.resource.ItemServiceResource;

public interface ItemServiceService {

	ItemServiceResource getItemService(Integer serviceId);

	ItemServiceResource create(ItemServiceResource itemServiceResource);

	ItemServicesDTO getServices(Integer itemId);

	void update(ItemServiceResource itemServiceResource);

	String delete(Integer serviceId);


}
