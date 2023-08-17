package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.dto.InserviceVsSpareResponseDTO;
import com.gotracrat.managelocation.entity.VwSpareMotor;
import com.gotracrat.managelocation.repository.ReportsDAO;
import com.gotracrat.managelocation.repository.VwSpareMotorRepository;
import com.gotracrat.managelocation.resource.ItemServicesResource;
import com.gotracrat.managelocation.utils.MockUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReportsServiceTest {
    @InjectMocks
    private ReportsServiceImpl reportsService;

    @Mock
    private VwSpareMotorRepository vwSpareMotorRepository;

    @Mock
    private ReportsDAO reportsDAO;

    @Test
    public void testGetSpareMotor() {
        when(vwSpareMotorRepository.findById(1)).thenReturn(MockUtils.mockGetSpareMotor());
        VwSpareMotor spareMotor = reportsService.getSpareMotor(1);
        Assert.assertNotNull(spareMotor);
        assertEquals(1,spareMotor.getItemId());
        assertEquals("1.5",spareMotor.getHp());
        verify(vwSpareMotorRepository, times(1)).findById(1);
    }

    @Test
    public void testGetServiceReportsForMonth() {
        when(reportsDAO.getServiceReports(any(),any(),any())).thenReturn(MockUtils.mockGetServiceReports());
        ItemServicesResource serviceReports = reportsService.getServiceReports(MockUtils.mockServiceReportsRequestForMonth());
        Assert.assertNotNull(serviceReports);
        assertEquals(true,serviceReports.getCompletedServices().get(0).getComplete());
        verify(reportsDAO, times(1)).getServiceReports(any(),any(),any());
    }

    @Test
    public void testGetServiceReportsForQuarter() {
        when(reportsDAO.getServiceReports(any(),any(),any())).thenReturn(MockUtils.mockGetServiceReports());
        ItemServicesResource serviceReports = reportsService.getServiceReports(MockUtils.mockServiceReportsRequestForQuarter());
        Assert.assertNotNull(serviceReports);
        assertEquals(true,serviceReports.getCompletedServices().get(0).getComplete());
        verify(reportsDAO, times(1)).getServiceReports(any(),any(),any());
    }

    @Test
    public void testGetServiceReportsForRange() {
        when(reportsDAO.getServiceReports(any(),any(),any())).thenReturn(MockUtils.mockGetServiceReports());
        ItemServicesResource serviceReports = reportsService.getServiceReports(MockUtils.mockServiceReportsRequestForRange());
        Assert.assertNotNull(serviceReports);
        assertEquals(true,serviceReports.getCompletedServices().get(0).getComplete());
        verify(reportsDAO, times(1)).getServiceReports(any(),any(),any());
    }

    @Test
    public void testGetInServiceVsSpareReports() {
        when(reportsDAO.getInServiceVsSpareReports(any())).thenReturn(MockUtils.mockGetInServiceVsSpareReports());
        InserviceVsSpareResponseDTO serviceReports = reportsService.getInServiceVsSpareReports(any());
        Assert.assertNotNull(serviceReports);
        //  assertEquals(true,serviceReports.getCompletedServices().get(0).getComplete());
        verify(reportsDAO, times(1)).getInServiceVsSpareReports(any());
    }
}
