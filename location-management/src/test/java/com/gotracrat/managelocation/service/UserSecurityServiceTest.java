package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.dto.UserLevelsDTO;
import com.gotracrat.managelocation.dto.UserRolesDTO;
import com.gotracrat.managelocation.entity.VwUserSecurity;
import com.gotracrat.managelocation.exception.ResourceNotFoundException;
import com.gotracrat.managelocation.repository.UserSecurityDAO;
import com.gotracrat.managelocation.repository.UserSecurityRepository;
import com.gotracrat.managelocation.repository.VwUserSecurityRepository;
import com.gotracrat.managelocation.resource.UserSecurityResource;
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
public class UserSecurityServiceTest {

    @InjectMocks
    private UserSecurityServiceImpl userSecurityService;

    @Mock
    private UserSecurityRepository userSecurityRepository;

    @Mock
    private UserSecurityDAO userSecurityDAO;

    @Mock
    private VwUserSecurityRepository vwUserSecurityRepository;

    @Test
    public void testGetUserSecurity() {
        when(userSecurityRepository.findById(any())).thenReturn(MockUtils.mockGetUserSecurity());
        UserSecurityResource userSecurityResource = userSecurityService.getUserSecurity("1", 453, 1);
        Assert.assertNotNull(userSecurityResource);
        assertEquals("1",userSecurityResource.getUserid());
        assertEquals(453,userSecurityResource.getCompanyid());
        verify(userSecurityRepository, times(1)).findById(any());
    }

    @Test
    public void testCreateOrUpdateUserSecurity() {
        when(userSecurityRepository.save(any())).thenReturn(MockUtils.createOrUpdateUserSecurity());
        UserSecurityResource userSecurityResource = userSecurityService.createOrUpdateUserSecurity(MockUtils.createOrUpdateUserSecurityResource(),MockUtils.mockUserLog());
        Assert.assertNotNull(userSecurityResource);
        assertEquals(453,userSecurityResource.getCompanyid());
        verify(userSecurityRepository, times(1)).save(any());
    }

    @Test
    public void testDeleteUserSecurity() {
        when(userSecurityRepository.existsById(any())).thenReturn(true);
        doNothing().when(userSecurityRepository).deleteById(any());
        userSecurityService.delete("1", 453,1,MockUtils.mockUserLog());
        verify(userSecurityRepository, times(1)).deleteById(any());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetUserSecurityNotFound() {
        when(userSecurityRepository.findById(any())).thenReturn(Optional.empty());
        userSecurityService.getUserSecurity("1", 453,1);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteWarrantyTypeNotFound() {
        when(userSecurityRepository.existsById(any())).thenReturn(false);
        userSecurityService.delete("1", 453,1, MockUtils.mockUserLog());
    }

    @Test
    public void testGetLevels() {
        when(userSecurityDAO.getLevels(any(),any())).thenReturn(MockUtils.mockGetLevels());
        List<UserLevelsDTO> userLevels = userSecurityService.getLevels("ypatel", 453);
        Assert.assertNotNull(userLevels);
        assertEquals("admin",userLevels.get(0).getRoleName());
        verify(userSecurityDAO, times(1)).getLevels(any(),any());
    }

    @Test
    public void testGetAllRolesForUser() {
        when(vwUserSecurityRepository.findByCompositePrimaryKeyUseridOrderByCompanyNameAscLocationNameAsc(any())).thenReturn(MockUtils.mockGetAllRolesForUser());
        List<VwUserSecurity> rolesOfUser = userSecurityService.getAllRolesForUser("1");
        Assert.assertNotNull(rolesOfUser);
        assertEquals("ypatel",rolesOfUser.get(0).getUserName());
        verify(vwUserSecurityRepository, times(1)).findByCompositePrimaryKeyUseridOrderByCompanyNameAscLocationNameAsc(any());
    }

    @Test
    public void testGetRolesForALoggedInUser() {
        when(vwUserSecurityRepository.getRolesForALoggedInUser(any(),any())).thenReturn(MockUtils.mockGetRolesForALoggedInUser());
        List<UserRolesDTO> rolesOfUser = userSecurityService.getRolesForALoggedInUser("1",453);
        Assert.assertNotNull(rolesOfUser);
        assertEquals("admin",rolesOfUser.get(0).getRoleName());
        assertEquals(1,rolesOfUser.get(0).getRank());
    }
}
