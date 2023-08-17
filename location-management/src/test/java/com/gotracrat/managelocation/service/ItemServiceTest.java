package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.dto.ChangeLogDTO;
import com.gotracrat.managelocation.dto.FindReplacementDTO;
import com.gotracrat.managelocation.dto.MasterSearchResponseDTO;
import com.gotracrat.managelocation.dto.WarehouseTagDTO;
import com.gotracrat.managelocation.entity.Attachment;
import com.gotracrat.managelocation.entity.Attributevalue;
import com.gotracrat.managelocation.entity.VwGetItem;
import com.gotracrat.managelocation.exception.BadRequestException;
import com.gotracrat.managelocation.exception.ResourceNotFoundException;
import com.gotracrat.managelocation.repository.*;
import com.gotracrat.managelocation.resource.ItemResource;
import com.gotracrat.managelocation.utils.MockUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private VwItemRepository vwItemRepository;

    @Mock
    private JournalRepository journalRepository;

    @Mock
    private ItemServiceRepository itemServiceRepository;

    @Mock
    private AttributevalueRepository attributevalueRepository;

    @Mock
    private ItemDAO itemDAOImpl;

    @Mock
    private VwGetItemRepository vwGetItemRepository;

    @Mock
    private AttachmentRepository attachmentRepository;

    @Test
    public void testVwGetItem() {
        when(vwGetItemRepository.findById(any())).thenReturn(MockUtils.mockVwGetItem(true));
        when(itemServiceRepository.findServiceDateByItemId(any())).thenReturn(MockUtils.getServiceDate());
        VwGetItem item = itemService.getItemById(389957);
        Assert.assertNotNull(item);
        assertEquals("MM00008", item.getTag());
        assertEquals(10, item.getDaysInService());
        assertEquals(9, item.getDaysDueForNextService());
        assertEquals("HP/KW/KVA", item.getAttributeValues().get(0).getName());
        assertEquals("39789", item.getAttributeValues().get(0).getAttributeNameID());
        assertEquals("7.5", item.getAttributeValues().get(0).getValue());
        verify(vwGetItemRepository, times(1)).findById(any());
        verify(itemServiceRepository, times(1)).findServiceDateByItemId(any());
    }

    @Test
    public void testVwGetItemWithInServiceOnAndServiceDateAsNull() {
        when(vwGetItemRepository.findById(any())).thenReturn(MockUtils.mockVwGetItem(false));
        when(itemServiceRepository.findServiceDateByItemId(any())).thenReturn(null);
        VwGetItem item = itemService.getItemById(389957);
        Assert.assertNotNull(item);
        assertEquals(0, item.getDaysInService());
        assertEquals(0, item.getDaysDueForNextService());
        verify(vwGetItemRepository, times(1)).findById(any());
        verify(itemServiceRepository, times(1)).findServiceDateByItemId(any());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testVwGetItemNotFound() {
        when(vwGetItemRepository.findById(any())).thenReturn(Optional.empty());
        itemService.getItemById(389957);
    }

    @Test(expected = BadRequestException.class)
    public void testDuplicateTagForCreateItem() {
        when(itemRepository.findByAsList(any(), any(), any())).thenReturn(MockUtils.mockTagAvailability());
        itemService.createItem(MockUtils.mockItemResource());
    }

    @Test
    public void testCreateItemWithAttributesAndAttachmentsAsNull() {
        when(itemRepository.findByAsList(any(), any(), any())).thenReturn(Collections.emptyList());
        when(itemRepository.save(any())).thenReturn(MockUtils.mockItem());
        ItemResource itemResource = itemService.createItem(MockUtils.mockItemResource());
        Assert.assertNotNull(itemResource);
        verify(itemRepository, times(1)).findByAsList(any(), any(), any());
        verify(itemRepository, times(1)).save(any());
    }

    @Test
    public void testCreateItem() {
        when(itemRepository.findByAsList(any(), any(), any())).thenReturn(Collections.emptyList());
        when(itemRepository.save(any())).thenReturn(MockUtils.mockItem());
        when(attributevalueRepository.saveAll(any())).thenReturn(Arrays.asList(new Attributevalue()));
        Attachment attachment = new Attachment();
        attachment.setAttachmentid(1);
        when(attachmentRepository.save(any())).thenReturn(attachment);
        when(itemRepository.setDefaultAttachmentId(any(), any())).thenReturn(1);
        ItemResource itemResource = itemService.createItem(MockUtils.mockItemResourceForCreateAndUpdate(true));
        Assert.assertNotNull(itemResource);
        verify(itemRepository, times(1)).findByAsList(any(), any(), any());
        verify(itemRepository, times(1)).save(any());
        verify(attributevalueRepository, times(1)).saveAll(any());
        verify(attachmentRepository, times(2)).save(any());
        verify(itemRepository, times(1)).setDefaultAttachmentId(any(), any());

    }

    @Test
    public void testItemDefaultImage() {
        when(itemRepository.existsById(any())).thenReturn(true);
        when(itemRepository.setDefaultAttachmentId(any(), any())).thenReturn(1);
        itemService.updateDefaultImageForItem(389957, 1);
        verify(itemRepository, times(1)).setDefaultAttachmentId(any(), any());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testItemDefaultImageNotFound() {
        when(itemRepository.existsById(any())).thenReturn(false);
        itemService.updateDefaultImageForItem(389957, 1);
    }

    @Test
    public void testGetChangeLog() {
        when(journalRepository.getChangeLogData(any(), any(), any(), any())).thenReturn(MockUtils.mockChangeLog());
        List<ChangeLogDTO> changeLog = itemService.getChangeLog(389957);
        Assert.assertNotNull(changeLog);
        assertEquals(3, changeLog.size());
        verify(journalRepository, times(1)).getChangeLogData(any(), any(), any(), any());
    }

    @Test
    public void testSearchTagsByType() {
        when(itemRepository.findByAsList(any(), any(), any())).thenReturn(MockUtils.mockTagAvailability());
        List<String> tags = itemService.searchTagsByType("MM00008", 2457);
        Assert.assertNotNull(tags);
        assertEquals(1, tags.size());
        verify(itemRepository, times(1)).findByAsList(any(), any(), any());
    }

    @Test
    public void testSearchTags() {
        when(vwItemRepository.findSuggestions(any(), any(), any())).thenReturn(MockUtils.mockTagSuggestions());
        final List<String> tags = itemService.searchTags("MM001", 279);
        Assert.assertNotNull(tags);
        assertEquals(3, tags.size());
        verify(vwItemRepository, times(1)).findSuggestions(any(), any(), any());
    }

    @Test
    public void testGetAllItemAttributesForReplacement() {
        when(itemDAOImpl.getAllItemAttributesForReplacement(any())).thenReturn(MockUtils.mockFindReplacementDTO());
        List<FindReplacementDTO> replacement = itemService.getAllItemAttributesForReplacement(389957);
        Assert.assertNotNull(replacement);
        assertEquals("Enclosure", replacement.get(0).getAttributeName());
        assertEquals("TEFC", replacement.get(0).getAttributeValue());
        verify(itemDAOImpl, times(1)).getAllItemAttributesForReplacement(any());
    }

    @Test
    public void testWarehouseTag() {
        when(itemRepository.getWarehouseTag(any(), any())).thenReturn(MockUtils.mockWareHouseTag());
        WarehouseTagDTO wareHouseTag = itemService.getWarehouseTag(389957);
        Assert.assertNotNull(wareHouseTag);
        assertEquals("ABC Vendor", wareHouseTag.getRepairVendorName());
        verify(itemRepository, times(1)).getWarehouseTag(any(), any());
    }

    @Test
    public void testWarehouseTagNoData() {
        when(itemRepository.getWarehouseTag(any(), any())).thenReturn(Collections.emptyList());
        WarehouseTagDTO wareHouseTag = itemService.getWarehouseTag(389957);
        assertNull(wareHouseTag.getActualCompletion());
        assertNull(wareHouseTag.getRepairVendorName());
        verify(itemRepository, times(1)).getWarehouseTag(any(), any());
    }

    @Test
    public void testDeleteItem() {
        when(itemRepository.existsById(any())).thenReturn(true);
        doNothing().when(itemRepository).deleteItem(any());
        itemService.deleteItem(389957, 287, MockUtils.mockUserLog());
        verify(itemRepository, times(1)).deleteItem(any());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteItemNotFound() {
        when(itemRepository.existsById(any())).thenReturn(false);
        itemService.deleteItem(389957, 279, MockUtils.mockUserLog());
    }

    @Test
    public void testMasterSearch() {
        when(itemDAOImpl.masterSearch(any())).thenReturn(MockUtils.mockMasterSearch());
        Map<String, Map<String, List<MasterSearchResponseDTO>>> searchResults = itemService
                .masterSearch(MockUtils.mockMasterSearchRequest());
        assertEquals("ABC Power Company", searchResults.keySet().stream().findFirst().get());
        assertEquals("Motor", searchResults.values().iterator().next().keySet().stream().findFirst().get());
        verify(itemDAOImpl, times(1)).masterSearch(any());
    }

    @Test
    public void testUpdateItem() {
        when(itemRepository.existsById(any())).thenReturn(true);
        when(itemRepository.save(any())).thenReturn(MockUtils.mockItem());
        when(attributevalueRepository.saveAll(any())).thenReturn(Arrays.asList(new Attributevalue()));
        itemService.updateItem(MockUtils.mockItemResourceForCreateAndUpdate(false));
        verify(itemRepository, times(1)).existsById(any());
        verify(itemRepository, times(1)).save(any());
        verify(attributevalueRepository, times(1)).saveAll(any());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testUpdateItemNotFound() {
        when(itemRepository.existsById(any())).thenReturn(false);
        itemService.updateItem(MockUtils.mockItemResourceForCreateAndUpdate(false));
    }

    @Test
    public void testGetItem() {
        when(itemDAOImpl.getItem(any())).thenReturn(MockUtils.mockItemResource());
		when(attributevalueRepository.findByCompositePrimaryKeyEntityidAndCompositePrimaryKeyEntitytypeid(any(), anyInt()))
				.thenReturn(MockUtils.mockAttributeValue());
        ItemResource item = itemService.getItem(389957);
        Assert.assertNotNull(item);
        assertEquals("MM00008", item.getTag());
        assertEquals(366504, item.getLocationid());
        assertEquals(3707, item.getStatusid());
        assertEquals(2457, item.getTypeId());
        verify(itemDAOImpl, times(1)).getItem(any());
		verify(attributevalueRepository, times(1))
				.findByCompositePrimaryKeyEntityidAndCompositePrimaryKeyEntitytypeid(any(), anyInt());
    }

    @Test
    public void testSearchItems() {
        when(itemDAOImpl.searchItems(any(), any(), any(), any())).thenReturn(MockUtils.mockListItemResource());
        itemService.searchItems(MockUtils.mockItemResource(), 278, true,
                "2345");
        Assert.assertNotNull(MockUtils.mockListItemResource());
        assertEquals("MM00008", MockUtils.mockListItemResource().get(0).getTag());
        assertEquals(366504, MockUtils.mockListItemResource().get(0).getLocationid());
        verify(itemDAOImpl, times(1)).searchItems(any(), any(), any(), any());
    }

    @Test
    public void testSearchItemsForTablet() {
        when(itemDAOImpl.searchItems(any())).thenReturn(MockUtils.mockSearchResponseDTO());
        itemService.searchItems(MockUtils.mockSearchRequestDTO());
        Assert.assertNotNull(MockUtils.mockSearchResponseDTO());
        assertEquals("MM00008", MockUtils.mockSearchResponseDTO().get(0).getTag());
        assertEquals(389957, MockUtils.mockSearchResponseDTO().get(0).getItemId());
        verify(itemDAOImpl, times(1)).searchItems(any());

    }
}
