package com.gotracrat.managelocation.repository;

import java.sql.SQLException;
import java.util.List;

import com.gotracrat.managelocation.dto.LocationMergeDTO;
import com.gotracrat.managelocation.resource.LocationResource;
import com.gotracrat.managelocation.entity.Location;

public interface LocationDAO {

	public List<Location> getAllLocations(int companyid);

	public Location getLocation(Integer locationid);

	public LocationMergeDTO mergeLocations(LocationMergeDTO location);

	public Boolean delete(Integer locationId);

	public void saveEntityTypeType(LocationResource LocationResource) throws SQLException;

	public LocationResource findBylocationid(Integer locationid);

	public int getType(Integer locationId);

	public LocationResource getCloneAddressFromParentLocation(Integer locationid, Integer companyid);

}
