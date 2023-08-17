package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.dto.UserLevelsDTO;
import com.gotracrat.managelocation.dto.UserRolesDTO;
import com.gotracrat.managelocation.entity.UserLog;
import com.gotracrat.managelocation.entity.UserSecurity;
import com.gotracrat.managelocation.entity.VwUserSecurity;
import com.gotracrat.managelocation.resource.UserSecurityResource;

import java.util.List;

/**
 * Service interface for UserSecurity.
 * @author prabhakar
 * @since 2018-10-27
 */
public interface UserSecurityService {

    /**
     * Recover an usersecurity following an id.
     *
     * @param userid    the given userId
     * @param companyid the given companyid
     * @return the usersecurity
     */
    public List<UserRolesDTO> getRolesForALoggedInUser(String userId, Integer companyId);

    /**
     * Recover an usersecurity following an id.
     *
     * @param userid the given id
     * @return the usersecurity
     */
    public UserSecurityResource getUserSecurity(String userid, Integer companyId, Integer locationId);

    /**
     * Perform an usersecurity deletion.
     *
     * @param userid    the given userid
     * @param companyid the given companyid
     * @param userLog   the given userLog
     * @return state of deletion (true if ok otherwise false)
     */
    public String delete(String userId, Integer companyId, Integer locationId, UserLog userLog);

    /**
     * Test usersecurity existence.
     *
     * @param usersecurity to check
     * @return true if author exist otherwise false
     */


    public UserSecurityResource createOrUpdateUserSecurity(UserSecurityResource usersecurity, UserLog userLog);

    public List<UserLevelsDTO> getLevels(String userName, Integer companyId);

    public List<VwUserSecurity> getAllRolesForUser(String userId);

}
