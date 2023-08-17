package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.exception.ResourceNotFoundException;
import com.gotracrat.managelocation.repository.WarrantyTypeRepository;
import com.gotracrat.managelocation.resource.WarrantyTypeResource;
import com.gotracrat.managelocation.utils.MockUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WarrantyTypeServiceTest {

    @InjectMocks
    private WarrantyTypeServiceImpl warrantyTypeService;

    @Mock
    private WarrantyTypeRepository warrantyTypeRepository;


    @Test
    public void testGetAllWarrantyTypeByCompanyId() {
        when(warrantyTypeRepository.findByCompanyCompanyid(453)).thenReturn(MockUtils.mockGetAllWarrantyTypeByCompanyId());
        List<WarrantyTypeResource> warrantyTypes = warrantyTypeService.getAllWarrantyTypeByCompanyId(453);
        Assert.assertNotNull(warrantyTypes);
        assertEquals(1,warrantyTypes.get(0).getWarrantytypeid());
        assertEquals(453,warrantyTypes.get(0).getCompanyid());
        verify(warrantyTypeRepository, times(1)).findByCompanyCompanyid(453);
    }

    @Test
    public void testGetWarrantyType() {
        when(warrantyTypeRepository.findById(any())).thenReturn(MockUtils.mockGetWarrantyType());
        WarrantyTypeResource warrantyType = warrantyTypeService.getWarrantyType(1);
        Assert.assertNotNull(warrantyType);
        assertEquals("Motor Only",warrantyType.getWarrantytype());
        verify(warrantyTypeRepository, times(1)).findById(any());
    }

    @Test
    public void testCreateWarrantyType() {
        when(warrantyTypeRepository.save(any())).thenReturn(MockUtils.mockCreateWarrantyType());
        WarrantyTypeResource warrantyType = warrantyTypeService.createWarrantyType(MockUtils.mockWarrantyTypeForCreateAndUpdate(),any());
        Assert.assertNotNull(warrantyType);
        assertEquals(1,warrantyType.getWarrantytypeid());
        assertEquals("Motor Only",warrantyType.getWarrantytype());
        verify(warrantyTypeRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateWarrantyType() {
        when(warrantyTypeRepository.existsById(any())).thenReturn(true);
        when(warrantyTypeRepository.save(any())).thenReturn(MockUtils.mockUpdateWarrantyType());
        warrantyTypeService.updateWarrantyType(MockUtils.mockWarrantyTypeForCreateAndUpdate(),any());
        verify(warrantyTypeRepository, times(1)).save(any());
    }

    @Test
    public void testDeleteUserSecurity() {
        when(warrantyTypeRepository.existsById(any())).thenReturn(true);
        doNothing().when(warrantyTypeRepository).deleteById(any());
        warrantyTypeService.delete(1,any());
        verify(warrantyTypeRepository, times(1)).deleteById(any());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetWarrantyTypeNotFound() {
        when(warrantyTypeRepository.findById(any())).thenReturn(Optional.empty());
        warrantyTypeService.getWarrantyType(2);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteWarrantyTypeNotFound() {
        when(warrantyTypeRepository.existsById(any())).thenReturn(false);
        warrantyTypeService.delete(2,any());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testUpdateWarrantyTypeNotFound() {
        when(warrantyTypeRepository.existsById(any())).thenReturn(false);
        warrantyTypeService.updateWarrantyType(MockUtils.mockWarrantyTypeForCreateAndUpdate(),any());
    }
}
