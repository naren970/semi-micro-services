/*
 * Created on 2018-07-15 ( Date ISO 2018-07-15 - Time 13:09:29 )
 * prabhakar
*/
package com.gotracrat.managelocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gotracrat.managelocation.entity.Location;

/**
 * Jpa repository for Location.
 * 
 * @author prabhakar
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Integer>, JpaSpecificationExecutor<Location> {

	public List<Location> findByCompanyCompanyid(int companyId);

	public List<Location> findByCompanyCompanyidAndParentlocationLocationid(int companyId, Integer parentID);

	public Location findBylocationid(Integer locationid);

	@Query(value="select l.LocationID from Location l where l.Name =:name and l.ParentId is null",nativeQuery = true)
    Integer findLocationIdByName(String name);
}