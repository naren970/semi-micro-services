package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.repository.AdvanceSearchDAO;
import com.gotracrat.managelocation.resource.AdvanceSearchResource;
import com.gotracrat.managelocation.resource.ItemAdsSearchResource;
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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdvanceSearchServiceTest {
    @InjectMocks
    private AdvanceSearchServiceImpl advanceSearchService;

    @Mock
    private AdvanceSearchDAO AdvanceSearchDAO;

    @Test
    public void testGetItemService() {
        when(AdvanceSearchDAO.getAdvanceSearchItemNoteByUser(any())).thenReturn(MockUtils.mockGetAdvanceSearchItemNoteByUser());
        when(AdvanceSearchDAO.getAdvanceSearchRepairLogByUser(any())).thenReturn(MockUtils.mockGetAdvanceSearchRepairLogByUser());
        AdvanceSearchResource advanceSearchResource = advanceSearchService.getAdvanceSearchRepairLogNoteByUser(MockUtils.getAdvanceSearchRepairLogNoteByUser());
        Assert.assertNotNull(advanceSearchResource);
        assertEquals(1,advanceSearchResource.getRepairlogList().get(0).getRepairLogId());
        assertEquals(1,advanceSearchResource.getItemNotes().get(0).getJournalId());
        verify(AdvanceSearchDAO, times(1)).getAdvanceSearchItemNoteByUser(any());
        verify(AdvanceSearchDAO, times(1)).getAdvanceSearchRepairLogByUser(any());
    }

    @Test
    public void testItemAdvancedSearch() {
        when(AdvanceSearchDAO.itemAdvancedSearch(any(),any())).thenReturn(MockUtils.mockItemAdsSearchResource());
        Map<String, List<ItemAdsSearchResource>> advanceSearchResource = advanceSearchService.itemAdvancedSearch(MockUtils.mockItemAdsRequestDTO());
        Assert.assertNotNull(advanceSearchResource);
        assertEquals(1,advanceSearchResource.values().iterator().next().get(0).getItemId());
        assertEquals("Motor",advanceSearchResource.values().iterator().next().get(0).getTypeName());
        verify(AdvanceSearchDAO, times(1)).itemAdvancedSearch(any(),any());
    }



}
