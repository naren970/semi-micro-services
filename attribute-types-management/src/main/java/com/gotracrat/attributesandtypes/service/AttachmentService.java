/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:54 )
 *
*/
package com.gotracrat.attributesandtypes.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;

import com.gotracrat.attributesandtypes.controller.resource.AllAttachmentDTO;
import com.gotracrat.attributesandtypes.controller.resource.AttachmentAddDTO;
import com.gotracrat.attributesandtypes.controller.resource.AttachmentResource;
import com.gotracrat.attributesandtypes.controller.resource.AttachmentUserLogDTO;
import com.gotracrat.attributesandtypes.entity.Attachment;
import com.gotracrat.attributesandtypes.entity.UserLog;

/**
 * Service interface for Attachment.
 * 
 * @author prabhakar
 */
public interface AttachmentService {
	
	/**
	 * Recover an attachment following an id.
	 * 
	 * @param moduleType
	 * @param entityid
	 * @param id         the given id
	 * @return the attachment
	 * @throws IOException
	 */
	public List<AttachmentResource> getAllAttachments(Integer companyID, String moduleType, int entityId)
			throws IOException;

	/**
	 * Recover an attachment following an id.
	 * 
	 * @param id the given id
	 * @return the attachment
	 */
	public Optional<Attachment> get(Integer attachmentId);

	/**
	 * Perform an attachment deletion.
	 * @param attachmentUserLogDTO 
	 * 
	 * @param id the given id
	 * @return state of deletion (true if ok otherwise false)
	 */
	public String delete(Integer attachmentId,UserLog userLog, AttachmentUserLogDTO attachmentUserLogDTO);

	/**
	 * Perform an attachment update.
	 * 
	 * @param attachmentResource to update
	 * @return state of update (true if ok otherwise false)
	 * @throws IOException
	 */
	public boolean save(AttachmentResource attachmentResource,UserLog userLog) throws IOException;

	/**
	 * Test attachment existence.
	 * 
	 * @param attachment to check
	 * @return true if author exist otherwise false
	 */
	public boolean exist(Attachment attachment);

	public Resource loadFileAsResource(int attachmentID);
	
	public List<AttachmentResource> createMultipleAttachments(AttachmentAddDTO attachmentAddDTO) throws IOException;

	public List<AllAttachmentDTO> getAllAttachments(String moduleType, int entityId);

}