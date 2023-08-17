package com.gotracrat.managelocation.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.gotracrat.managelocation.dto.ItemServicesDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gotracrat.managelocation.resource.ItemServiceResource;
import com.gotracrat.managelocation.entity.ItemService;
import com.gotracrat.managelocation.exception.ResourceNotFoundException;
import com.gotracrat.managelocation.repository.ItemServiceRepository;
import com.gotracrat.managelocation.utils.GoTracratConstants;

@Service
public class ItemServiceServiceImpl implements ItemServiceService {

	@Autowired
	ItemServiceRepository itemServiceRepository;

	/**
	 * This method is used for to Get ItemService
	 *
	 * @return ItemServiceResource
	 */
	@Override
	public ItemServiceResource getItemService(Integer serviceId) {
		Optional<ItemService> itemServiceOptional = itemServiceRepository.findById(serviceId);
		if (itemServiceOptional.isPresent()) {
			ItemService itemService = itemServiceOptional.get();
			return buildResourceFromEntity(itemService);
		}
		throw new ResourceNotFoundException(GoTracratConstants.INVALID_SERVICE_ID + serviceId);
	}

	private ItemServiceResource buildResourceFromEntity(ItemService itemService) {
		ItemServiceResource itemServiceResource = new ItemServiceResource();
		BeanUtils.copyProperties(itemService, itemServiceResource);
		return itemServiceResource;
	}

	/**
	 * This method is used for to Get ItemServices
	 *
	 * @return ItemServicesDTO
	 */
	@Override
	public ItemServicesDTO getServices(Integer itemId) {
		ItemServicesDTO itemServicesDTO = new ItemServicesDTO();
		List<ItemService> itemServices = itemServiceRepository.findByitemId(itemId);
		List<ItemService> completedServices = itemServices.stream().filter(ItemService::getComplete)
				.collect(Collectors.toList());
		List<ItemService> inCompletedServices = itemServices.stream().filter(inCompleted -> !inCompleted.getComplete())
				.collect(Collectors.toList());
		itemServicesDTO.setCompletedServices(completedServices);
		itemServicesDTO.setInCompletedServices(inCompletedServices);
		return itemServicesDTO;
	}

	/**
	 * This method is used for to create ItemService
	 *
	 * @return ItemServiceResource
	 */
	@Override
	public ItemServiceResource create(ItemServiceResource itemServiceResource) {
		ItemService itemService = itemServiceRepository.save(buildEntityFromResource(itemServiceResource));
		return buildResourceFromEntity(itemService);
	}

	private ItemService buildEntityFromResource(ItemServiceResource itemServiceResource) {
		ItemService itemService = new ItemService();
		BeanUtils.copyProperties(itemServiceResource, itemService);
		return itemService;
	}

	/**
	 * This method is used for to update ItemService
	 */
	@Override
	public void update(ItemServiceResource itemServiceResource) {
		if (itemServiceRepository.existsById(itemServiceResource.getServiceId())) {
			itemServiceRepository.save(buildEntityFromResource(itemServiceResource));
		} else {
			throw new ResourceNotFoundException(
					GoTracratConstants.INVALID_SERVICE_ID + itemServiceResource.getServiceId());
		}
	}

	/**
	 * This method is used for to delete ItemService
	 */
	@Override
	public String delete(Integer serviceId) {
		if (itemServiceRepository.existsById(serviceId)) {
			itemServiceRepository.deleteById(serviceId);
			return GoTracratConstants.ITEM_SERVICE_DELETED;
		} else {
			throw new ResourceNotFoundException(GoTracratConstants.INVALID_SERVICE_ID + serviceId);
		}
	}
}
