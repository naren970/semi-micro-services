package com.gotracrat.attributesandtypes.repository;

import java.util.List;

import com.gotracrat.attributesandtypes.controller.resource.AttachmentDTO;

public interface AttachmentDAO {

public 	List<AttachmentDTO> getAllAttachments(int entityid);

public List<AttachmentDTO> getAllAttachmentsForNotes(int entityid, int companyID, int journalTypeId, int entitytypeid);

	
	
	
}
