package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.entity.FailureType;
import com.gotracrat.managelocation.entity.UserLog;
import com.gotracrat.managelocation.exception.ResourceNotFoundException;
import com.gotracrat.managelocation.repository.FailureTypeRepository;
import com.gotracrat.managelocation.utils.MockUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FailureTypeServiceTest {

    @InjectMocks
    private FailureTypeServiceImpl failureTypeService;

    @Mock
    private FailureTypeRepository failureTypeRepository;

    @Test
    public void testCreateFailureType() {
        when(failureTypeRepository.save(any())).thenReturn(MockUtils.mockCreateFailureType());
        FailureType failureType = failureTypeService.createFailureType(MockUtils.mockCreateFailureType());
        Assert.assertNotNull(failureType);
        assertEquals(1,failureType.getFailuretypeid());
        assertEquals("Water In Motor",failureType.getCauses());
        verify(failureTypeRepository, times(1)).save(any());
    }

    /*@Test
    public void testUpdateFailureType() {
        when(failureTypeRepository.save(any())).thenReturn(MockUtils.mockCreateFailureType());
        when(failureTypeRepository.existsById(any())).thenReturn(true);
        String failureType = failureTypeService.updateFailureType(MockUtils.mockCreateFailureType());
        Assert.assertNotNull(failureType);
        assertEquals("Failure Type Updated Successfully",failureType);
        verify(failureTypeRepository, times(1)).save(any());
    }*/

    @Test
    public void testGetFailureTypeAndCause() {
        when(failureTypeRepository.findByItemtypeid(any())).thenReturn(MockUtils.mockGetFailureTypeAndCause());
        Map<String, List<String>> failureTypes = failureTypeService.getFailureTypeAndCause(453,1);
        Assert.assertNotNull(failureTypes);
        assertEquals("Water In Motor",failureTypes.values().iterator().next().get(0));
        verify(failureTypeRepository, times(1)).findByItemtypeid(any());
    }

    @Test
    public void testDeleteFailureType() {
        when(failureTypeRepository.existsById(1)).thenReturn(true);
        doNothing().when(failureTypeRepository).deleteById(1);
        failureTypeService.deleteFailureType(1,new UserLog());
        verify(failureTypeRepository, times(1)).deleteById(any());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteItemServiceNotFound() {
        when(failureTypeRepository.existsById(any())).thenReturn(false);
        failureTypeService.deleteFailureType(2,new UserLog());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testUpdateNotFound() {
        when(failureTypeRepository.existsById(any())).thenReturn(false);
        failureTypeService.updateFailureType(new FailureType());
    }

}
