package com.gotracrat.managelocation.service;

import java.util.List;

import com.gotracrat.managelocation.resource.WarrantyTypeResource;
import com.gotracrat.managelocation.entity.UserLog;

/**
 * Service interface for WarrantyType.
 * @since 13-08-2018
 * @author prabhakar
 */
public interface WarrantyTypeService {

	/**
	 * Recover an status following an id.
	 * 
	 * @param id
	 *            the given id
	 * @return the Warrantytype
	 */
	List<WarrantyTypeResource> getAllWarrantyTypeByCompanyId(Integer companyId);

	/**
	 * Recover an warrantytype following an id.
	 * 
	 * @param id
	 *            the given id
	 * @return the warrantytype
	 */
	WarrantyTypeResource getWarrantyType(Integer warrantyTypeId);


	/**
	 * Perform an warrantytype creation.
	 * 
	 * @param warrantytype
	 *            to create
	 * @return created warrantytype
	 */
	WarrantyTypeResource createWarrantyType(WarrantyTypeResource warrantyTypeResource, UserLog userLog);

	/**
	 * Perform an warrantytype update.
	 * 
	 * @param warrantytype
	 *            to update
	 * @return state of update (true if ok otherwise false)
	 */
	void updateWarrantyType(WarrantyTypeResource warrantytypeResource, UserLog userLog);

	/**
	 * Perform an warrantytype deletion.
	 *
	 * @param id
	 *            the given id
	 * @return state of deletion (true if ok otherwise false)
	 */
	String delete(Integer warrantyTypeId,UserLog userLog);

}
