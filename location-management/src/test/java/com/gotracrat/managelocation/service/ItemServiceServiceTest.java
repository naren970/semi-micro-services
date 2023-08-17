package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.dto.ItemServicesDTO;
import com.gotracrat.managelocation.exception.ResourceNotFoundException;
import com.gotracrat.managelocation.repository.ItemServiceRepository;
import com.gotracrat.managelocation.resource.ItemServiceResource;
import com.gotracrat.managelocation.utils.MockUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceServiceTest {

    @InjectMocks
    private ItemServiceServiceImpl itemServiceService;

    @Mock
    private ItemServiceRepository itemServiceRepository;

    @Test
    public void testGetItemService() {
        when(itemServiceRepository.findById(1)).thenReturn(MockUtils.mockGetItemService());
        ItemServiceResource itemService = itemServiceService.getItemService(1);
        Assert.assertNotNull(itemService);
        assertEquals(1,itemService.getItemId());
        assertEquals(1,itemService.getServiceId());
        verify(itemServiceRepository, times(1)).findById(1);
    }

    @Test
    public void testGetServices() {
        when(itemServiceRepository.findByitemId(1)).thenReturn(MockUtils.mockGetServices());
        ItemServicesDTO itemServices = itemServiceService.getServices(1);
        Assert.assertNotNull(itemServices);
        assertEquals(1,itemServices.getCompletedServices().get(0).getItemId());
        verify(itemServiceRepository, times(1)).findByitemId(1);
    }

    @Test
    public void testCreateItemService() {
        when(itemServiceRepository.save(any())).thenReturn(MockUtils.mockGetServices().get(0));
        ItemServiceResource itemService = itemServiceService.create(MockUtils.mockItemServiceResource());
        Assert.assertNotNull(itemService);
        assertEquals(1,itemService.getItemId());
        verify(itemServiceRepository, times(1)).save(any());
    }

    @Test
    public void testUpDateItemService() {
        when(itemServiceRepository.existsById(any())).thenReturn(true);
        when(itemServiceRepository.save(any())).thenReturn(MockUtils.mockGetServices().get(0));
        itemServiceService.update(MockUtils.mockItemServiceResource());
        verify(itemServiceRepository, times(1)).save(any());
    }

    @Test
    public void testDelete() {
        when(itemServiceRepository.existsById(any())).thenReturn(true);
        doNothing().when(itemServiceRepository).deleteById(any());
        itemServiceService.delete(1);
        verify(itemServiceRepository, times(1)).deleteById(any());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetItemServiceNotFound() {
        when(itemServiceRepository.findById(any())).thenReturn(Optional.empty());
        itemServiceService.getItemService(2);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testUpdateNotFound() {
        when(itemServiceRepository.existsById(any())).thenReturn(false);
        itemServiceService.update(MockUtils.mockItemServiceResource());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteItemServiceNotFound() {
        when(itemServiceRepository.existsById(any())).thenReturn(false);
        itemServiceService.delete(2);
    }
}
