/*
 * Created on 2018-10-27 ( Date ISO 2018-10-27 - Time 19:57:22 )
 * Generated by
 */
package com.gotracrat.managelocation.service;

import java.sql.SQLException;
import java.util.List;

import com.gotracrat.managelocation.resource.AspnetMembershipResource;
import com.gotracrat.managelocation.resource.AspnetUsersResource;
import com.gotracrat.managelocation.resource.ProfileResource;
import com.gotracrat.managelocation.entity.AspnetUsers;
import com.gotracrat.managelocation.entity.UserLog;

/**
 * Service interface for AspnetUsers.
 *
 * @author Prabhakar
 */
public interface UsersService {

    public AspnetUsers getUserIDByUserName(String userName);

    public Integer getUserCountByUserName(String userName);

    public Boolean exist(AspnetUsers aspnetUsers);

    public AspnetUsersResource create(AspnetUsersResource aspnetUsers, UserLog userLog);

    public void save(ProfileResource profileResource);

    public List<ProfileResource> getUserNames(Integer companyid) throws SQLException;

    public AspnetMembershipResource changePassword(AspnetMembershipResource aspnetMembershipResource);

    public List<ProfileResource> getUserProfilesAsAdmin(Integer companyid) throws SQLException;

    public String delete(String userId, Integer profileId, UserLog userLog);

}
