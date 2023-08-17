/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:54 )
 * 
*/
package com.gotracrat.attributesandtypes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gotracrat.attributesandtypes.controller.resource.AllAttachmentDTO;
import com.gotracrat.attributesandtypes.entity.Attachment;

/**
 * Jpa repository for Attachment.
 * 
 * @author prabhakar
 */
@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer>, JpaSpecificationExecutor<Attachment> {

	public List<Attachment> findAllByentityid(Integer companyID);

	public List<Attachment> findByEntityidAndEntitytypeidAndEntityid(int companyID, int entityTypeId, int entityid);

	@Query(value="Select new com.gotracrat.attributesandtypes.controller.resource.AllAttachmentDTO(at.attachmentid,at.filename,at.description,at.dateadded,at.addedby,at.isNew,at.contenttype) "
			+ "from Attachment at where at.entityid = ?1 and at.entitytypeid = ?2 order by at.dateadded desc")
	public List<AllAttachmentDTO> getAllAttachments(Integer entityId,Integer entityTypeId);
	
	@Query("Select c.filePath from Attachment c where c.attachmentid = ?1")
	public String findFilePathByAttachmentId(Integer attachmentId);

}