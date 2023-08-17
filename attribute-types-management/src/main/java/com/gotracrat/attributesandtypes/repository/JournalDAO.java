package com.gotracrat.attributesandtypes.repository;

import com.gotracrat.attributesandtypes.controller.resource.AttachmentResource;
import com.gotracrat.attributesandtypes.controller.resource.ItemChangeLog;

public interface JournalDAO {

	public Integer getLocationId(Integer entityid);

	public AttachmentResource getEntityNameAndCompanyId(AttachmentResource attachmentResource);

	public ItemChangeLog getLocationNames(Integer locationId, Integer previous_LocationId);

	public ItemChangeLog getStatusNames(Integer statusId, Integer previous_StatusId);

}
