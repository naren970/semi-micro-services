package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.dto.LocationNamesDTO;
import com.gotracrat.managelocation.dto.UserLevelsDTO;
import com.gotracrat.managelocation.dto.UserRolesDTO;
import com.gotracrat.managelocation.entity.AspnetUsers;
import com.gotracrat.managelocation.resource.ProfileResource;

import java.sql.SQLException;
import java.util.List;

public interface UserSecurityDAO {
    public List<UserLevelsDTO> getLevels(String userName, Integer companyId);

    public List<LocationNamesDTO> getLocationNames(String userid, Integer companyid);

    public AspnetUsers getUserIDByUserName(String userName);

    public List<ProfileResource> findAllBycompanyid(Integer companyid) throws SQLException;

    public Integer getCountOfLocationIDByUserId(int companyid, String userid);

    public List<ProfileResource> getUserProfilesAsAdmin(Integer companyid) throws SQLException;

    public int getRankForUserInAllLocations(Integer companyId, String userId);
}
