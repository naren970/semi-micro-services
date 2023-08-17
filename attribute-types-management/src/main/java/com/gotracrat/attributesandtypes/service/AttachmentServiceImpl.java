/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:54 )
 *
*/
package com.gotracrat.attributesandtypes.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.gotracrat.attributesandtypes.exception.InternalServerException;
import com.gotracrat.attributesandtypes.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.gotracrat.attributesandtypes.controller.resource.AllAttachmentDTO;
import com.gotracrat.attributesandtypes.controller.resource.AttachmentAddDTO;
import com.gotracrat.attributesandtypes.controller.resource.AttachmentResource;
import com.gotracrat.attributesandtypes.controller.resource.AttachmentUserLogDTO;
import com.gotracrat.attributesandtypes.entity.Attachment;
import com.gotracrat.attributesandtypes.entity.UserLog;
import com.gotracrat.attributesandtypes.repository.AttachmentRepository;
import com.gotracrat.attributesandtypes.repository.UserLogRepository;
import com.gotracrat.attributesandtypes.util.FileUtils;
import com.gotracrat.attributesandtypes.utils.EntityTypeIDEnum;
import com.gotracrat.attributesandtypes.utils.GoTracratConstants;
import com.gotracrat.attributesandtypes.utils.GotracratUtility;
import com.gotracrat.attributesandtypes.utils.ModulesEnum;

/**
 * Service implementation for Attachment.
 *
 * @author prabhakar
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {


	private Path fileStorageLocation;

	@Autowired
	private AttachmentRepository attachmentRepository;

	@Autowired
	private UserLogRepository userLogRepository;



	@Override
	public List<AttachmentResource> getAllAttachments(Integer companyID, String moduleType, int entityId)
			throws IOException {
		int entityTypeId = findEntityTypeId(companyID, moduleType);
		if (entityTypeId != 0) {
			List<Attachment> attachment = attachmentRepository.findByEntityidAndEntitytypeidAndEntityid(companyID,
					entityTypeId, entityId);
			return this.buildAttachmentResources(attachment);
		}
		return Collections.emptyList();
	}

	@Override
	public Optional<Attachment> get(Integer attachmentid) {
		return attachmentRepository.findById(attachmentid);
	}

	@Override
	public String delete(Integer attachmentId, UserLog userLog, AttachmentUserLogDTO attachmentUserLogDTO) {
		Optional<Attachment> attachmentOptional = attachmentRepository.findById(attachmentId);
		Attachment attachment = null;
		if (attachmentOptional.isPresent()) {
			attachment = attachmentOptional.get();
		}
		if (attachment != null) {
			userLog.setActionComment(getActionComment(attachment, attachmentUserLogDTO, userLog.getUserName()));
			attachmentRepository.deleteById(attachmentId);
			return GoTracratConstants.ATTACHMENT_DELETED;
		}
		throw new ResourceNotFoundException(GoTracratConstants.INVALID_ATTACHMENT_ID + attachmentId);
	}

	private String getActionComment(Attachment attachment, AttachmentUserLogDTO attachmentUserLogDTO, String userName) {
		Integer entityTypeId = attachment.getEntitytypeid();
		String actionComment = null;
		if (entityTypeId.equals(EntityTypeIDEnum.COMPANY_TYPE_ENTITYTYPE_ID.entityTypeID())) {

			actionComment = "Company Attachment:" + attachment.getFilename() + GoTracratConstants.DELETED_BY + userName;

		} else if (entityTypeId.equals(EntityTypeIDEnum.ITEM_TYPE_ENTITYTYPE_ID.entityTypeID())) {

			actionComment = "Item " + attachmentUserLogDTO.getItemTypeName() + ":" + attachmentUserLogDTO.getItemTag()
					+ " Attachment:" + attachment.getFilename() + GoTracratConstants.DELETED_BY + userName;

		} else if (entityTypeId.equals(EntityTypeIDEnum.ITEM_TYPE_NOTE_ENTITYTYPE_ID.entityTypeID())) {

			if ((attachmentUserLogDTO.getNoteType().equalsIgnoreCase(GoTracratConstants.COMPANY_NOTE_ATTACHMENT_TYPE))) {

				actionComment = "Company Note Attachment:" + attachment.getFilename() + " of note:"
						+ attachmentUserLogDTO.getNoteName() + GoTracratConstants.DELETED_BY + userName;

			} else if ((attachmentUserLogDTO.getNoteType()
					.equalsIgnoreCase(GoTracratConstants.ITEM_NOTE_ATTACHMENT_TYPE))) {

				actionComment = "Item Note Attachment:" + attachment.getFilename() + " of item "
						+ attachmentUserLogDTO.getItemTypeName() + ":" + attachmentUserLogDTO.getItemTag() + " note:"
						+ attachmentUserLogDTO.getNoteName() + GoTracratConstants.DELETED_BY + userName;

			} else if ((attachmentUserLogDTO.getNoteType()
					.equalsIgnoreCase(GoTracratConstants.LOCATION_NOTE_ATTACHMENT_TYPE))) {

				actionComment = "Location Note Attachment:" + attachment.getFilename() + " of location: "
						+ attachmentUserLogDTO.getLocationName() + " note:" + attachmentUserLogDTO.getNoteName()
						+ GoTracratConstants.DELETED_BY + userName;

			} else if (attachmentUserLogDTO.getNoteType()
					.equalsIgnoreCase(GoTracratConstants.ITEM_CHANGELOG_ATTACHMENT_TYPE)) {
				actionComment = "Item ChangeLog Attachment:" + attachment.getFilename() + " of item "
						+ attachmentUserLogDTO.getItemTypeName() + ":" + attachmentUserLogDTO.getItemTag() + " note:"
						+ attachmentUserLogDTO.getNoteName() + GoTracratConstants.DELETED_BY + userName;
			}
		} else if (entityTypeId.equals(EntityTypeIDEnum.ITEM_REPAIR_ENTITYTYPE_ID.entityTypeID())) {

			actionComment = "Item Repair Attachment" + attachment.getFilename() + " of item "
					+ attachmentUserLogDTO.getItemTypeName() + ":" + attachmentUserLogDTO.getItemTag()
					+ " repair with PO:" + attachmentUserLogDTO.getPoNumber() + " JOB:"
					+ attachmentUserLogDTO.getJobNumber() + GoTracratConstants.DELETED_BY + userName;

		}

		return actionComment;

	}

	@Override
	public boolean save(AttachmentResource attachmentResource, UserLog userLog) throws IOException {
		final Integer pk = attachmentResource.getAttachmentid();
		if (attachmentRepository.findById(pk).isPresent()) {
			Attachment attachment = this.buildAttachment(attachmentResource);
			if (attachment != null) {
				attachment.setAttachmentid(pk);
				attachmentRepository.save(attachment);
				BeanUtils.copyProperties(attachment.getUserlog(), userLog);
				return true;
			}

		}
		return false;
	}

	@Override
	public boolean exist(Attachment attachment) {
		return attachmentRepository.existsById(attachment.getAttachmentid());
	}

	private int findEntityTypeId(int companyId, String moduleType) {
		if (!moduleType.isEmpty() && companyId != 0) {
			if (moduleType.equalsIgnoreCase(GoTracratConstants.COMPANY_MODULE_TYPE)) {
				return EntityTypeIDEnum.COMPANY_TYPE_ENTITYTYPE_ID.entityTypeID();
			} else if (moduleType.equalsIgnoreCase(GoTracratConstants.LOCATION_MODULE_TYPE)) {
				return EntityTypeIDEnum.LOCATION_TYPE_ENTITYTYPE_ID.entityTypeID();
			} else if (moduleType.equalsIgnoreCase(GoTracratConstants.ITEM_MODULE_TYPE)) {
				return EntityTypeIDEnum.ITEM_TYPE_ENTITYTYPE_ID.entityTypeID();
			} else if (moduleType.equalsIgnoreCase(GoTracratConstants.ITEM_MODULE_NOTE_TYPE)) {
				return EntityTypeIDEnum.ITEM_TYPE_NOTE_ENTITYTYPE_ID.entityTypeID();
			} else if (moduleType.equalsIgnoreCase(GoTracratConstants.ITEM_REPAIR_TYPE)) {
				return EntityTypeIDEnum.ITEM_REPAIR_ENTITYTYPE_ID.entityTypeID();
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	private Attachment buildAttachment(AttachmentResource attachmentResource) {
		Attachment attachment = new Attachment();
		BeanUtils.copyProperties(attachmentResource, attachment);
		attachment.setAttachment(attachmentResource.getAttachmentFile().getBytes());
		attachment = setEntityTypeIdandUserLog(attachment, attachmentResource, GoTracratConstants.UPDATED_BY);
		return attachment;
	}

	private Attachment setEntityTypeIdandUserLog(Attachment attachment, AttachmentResource attachmentResource,
			String type) {
		AttachmentUserLogDTO attachmentUserLogDTO = attachmentResource.getAttachmentUserLogDTO();
		String modifiedBy = null;
		if (type.equalsIgnoreCase(GoTracratConstants.CREATED_BY)) {
			modifiedBy = attachmentResource.getAddedby();
		} else if (type.equalsIgnoreCase(GoTracratConstants.UPDATED_BY)) {
			modifiedBy = attachmentResource.getUpdatedBy();
		}

		if (!attachmentResource.getModuleType().isEmpty() && attachmentResource.getEntityid() != null
				&& attachmentResource.getEntityid() != 0) {
			if (attachmentResource.getModuleType().equalsIgnoreCase(GoTracratConstants.COMPANY_MODULE_TYPE)) {
				attachment.setEntitytypeid(EntityTypeIDEnum.COMPANY_TYPE_ENTITYTYPE_ID.entityTypeID());

				String actionComment = "Company Attachment:" + attachmentResource.getFilename() + type + modifiedBy;
				UserLog userLog = GotracratUtility.getUserLog(modifiedBy, attachmentResource.getCompanyID(),
						ModulesEnum.ATTACHMENT.getModule(), actionComment);
				attachment.setUserlog(userLog);
			} else if (attachmentResource.getModuleType().equalsIgnoreCase(GoTracratConstants.LOCATION_MODULE_TYPE)) {
				attachment.setEntitytypeid(EntityTypeIDEnum.LOCATION_TYPE_ENTITYTYPE_ID.entityTypeID());
			} else if (attachmentResource.getModuleType().equalsIgnoreCase(GoTracratConstants.ITEM_MODULE_TYPE)) {
				attachment.setEntitytypeid(EntityTypeIDEnum.ITEM_TYPE_ENTITYTYPE_ID.entityTypeID());

				String actionComment = "Item " + attachmentUserLogDTO.getItemTypeName() + ":"
						+ attachmentUserLogDTO.getItemTag() + " Attachment:" + attachmentResource.getFilename() + type
						+ modifiedBy;
				UserLog userLog = GotracratUtility.getUserLog(modifiedBy, attachmentResource.getCompanyID(),
						ModulesEnum.ATTACHMENT.getModule(), actionComment);
				attachment.setUserlog(userLog);

			} else if (attachmentResource.getModuleType().equalsIgnoreCase(GoTracratConstants.ITEM_MODULE_NOTE_TYPE)) {
				attachment.setEntitytypeid(EntityTypeIDEnum.ITEM_TYPE_NOTE_ENTITYTYPE_ID.entityTypeID());
				if (attachmentUserLogDTO.getNoteType()
						.equalsIgnoreCase(GoTracratConstants.COMPANY_NOTE_ATTACHMENT_TYPE)) {
					String actionComment = "Company Note Attachment:" + attachmentResource.getFilename() + " of note:"
							+ attachmentUserLogDTO.getNoteName() + type + modifiedBy;
					UserLog userLog = GotracratUtility.getUserLog(modifiedBy, attachmentResource.getCompanyID(),
							ModulesEnum.ATTACHMENT.getModule(), actionComment);
					attachment.setUserlog(userLog);
				} else if (attachmentUserLogDTO.getNoteType()
						.equalsIgnoreCase(GoTracratConstants.ITEM_NOTE_ATTACHMENT_TYPE)) {
					String actionComment = "Item Note Attachment:" + attachmentResource.getFilename() + " of item "
							+ attachmentUserLogDTO.getItemTypeName() + ":" + attachmentUserLogDTO.getItemTag()
							+ " note:" + attachmentUserLogDTO.getNoteName() + type + modifiedBy;
					UserLog userLog = GotracratUtility.getUserLog(modifiedBy, attachmentResource.getCompanyID(),
							ModulesEnum.ATTACHMENT.getModule(), actionComment);
					attachment.setUserlog(userLog);
				} else if (attachmentUserLogDTO.getNoteType()
						.equalsIgnoreCase(GoTracratConstants.LOCATION_NOTE_ATTACHMENT_TYPE)) {
					String actionComment = "Location Note Attachment:" + attachmentResource.getFilename()
							+ " of location: " + attachmentUserLogDTO.getLocationName() + " note:"
							+ attachmentUserLogDTO.getNoteName() + type + modifiedBy;
					UserLog userLog = GotracratUtility.getUserLog(modifiedBy, attachmentResource.getCompanyID(),
							ModulesEnum.ATTACHMENT.getModule(), actionComment);
					attachment.setUserlog(userLog);
				} else if (attachmentUserLogDTO.getNoteType()
						.equalsIgnoreCase(GoTracratConstants.ITEM_CHANGELOG_ATTACHMENT_TYPE)) {
					String actionComment = "Item ChangeLog Attachment:" + attachmentResource.getFilename() + " of item "
							+ attachmentUserLogDTO.getItemTypeName() + ":" + attachmentUserLogDTO.getItemTag()
							+ " note:" + attachmentUserLogDTO.getNoteName() + type + modifiedBy;
					UserLog userLog = GotracratUtility.getUserLog(modifiedBy, attachmentResource.getCompanyID(),
							ModulesEnum.ATTACHMENT.getModule(), actionComment);
					attachment.setUserlog(userLog);
				}

			} else if (attachmentResource.getModuleType().equalsIgnoreCase(GoTracratConstants.ITEM_REPAIR_TYPE)) {
				attachment.setEntitytypeid(EntityTypeIDEnum.ITEM_REPAIR_ENTITYTYPE_ID.entityTypeID());

				String actionComment = "Item Repair Attachment:" + attachmentResource.getFilename() + " of item "
						+ attachmentUserLogDTO.getItemTypeName() + ":" + attachmentUserLogDTO.getItemTag()
						+ " repair with PO:" + attachmentUserLogDTO.getPoNumber() + " JOB:"
						+ attachmentUserLogDTO.getJobNumber() + type + modifiedBy;
				UserLog userLog = GotracratUtility.getUserLog(modifiedBy, attachmentResource.getCompanyID(),
						ModulesEnum.ATTACHMENT.getModule(), actionComment);
				attachment.setUserlog(userLog);
			} else {
				return null;
			}
		} else {
			return null;
		}
		return attachment;
	}

	private Attachment buildAttachmentNew(AttachmentResource attachmentResource) throws IOException {
		Attachment attachment = new Attachment();
		BeanUtils.copyProperties(attachmentResource, attachment);
		byte[] compressed = GotracratUtility.compress(attachmentResource.getAttachmentFile());
		attachment.setAttachment(compressed);
		attachment.setIsNew(attachmentResource.getIsNew());
		attachment = setEntityTypeIdandUserLog(attachment, attachmentResource, GoTracratConstants.CREATED_BY);
		return attachment;
	}

	private List<AttachmentResource> buildAttachmentResources(List<Attachment> attachmentList) {
		final List<AttachmentResource> attachmentResourceList = new ArrayList<>();
		attachmentList.forEach(attachment -> {
			AttachmentResource attachmentResource = new AttachmentResource();
			BeanUtils.copyProperties(attachment, attachmentResource);
			attachmentResource.setByteData(attachment.getAttachment());
			attachmentResource.setDateadded(attachment.getDateadded());
			attachmentResource.setIsNew(attachment.getIsNew());
			try {
				attachmentResource.setAttachmentFile(GotracratUtility.decompress(attachment.getAttachment()));
			} catch (IOException e) {
				throw new InternalServerException(e);
			}
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ssZ");
			String dateAddedOn = dateFormat.format(attachment.getDateadded());
			attachmentResource.setDateaddedOn(dateAddedOn);
			attachmentResourceList.add(attachmentResource);
		});
		return attachmentResourceList;
	}

	@Override
	public Resource loadFileAsResource(int attachmentID) {
		String attachmentfilePath = attachmentRepository.findFilePathByAttachmentId(attachmentID);
		this.fileStorageLocation = Paths.get(FileUtils.generateXlsFileDirectory()).toAbsolutePath().normalize();
		try {
			Path filePath = this.fileStorageLocation.resolve(attachmentfilePath).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;		}
		}
		catch (IOException ex) {
			throw new InternalServerException(ex);
		}
		return null;

	}

	@Override
	public List<AttachmentResource> createMultipleAttachments(AttachmentAddDTO attachmentAddDTO) throws IOException {
		List<UserLog> userLogList = new ArrayList<>();
		List<AttachmentResource> attachmentResourceList = attachmentAddDTO.getAttachmentResourceList();
		attachmentResourceList.forEach(attachmentResource -> {
					attachmentResource.setAttachmentUserLogDTO(attachmentAddDTO.getAttachmentUserLogDTO());
			Attachment attachment = null;
			try {
				attachment = this.buildAttachmentNew(attachmentResource);
			} catch (IOException e) {
				throw new InternalServerException(e);
			}
			if (attachment != null) {
						attachment.setAttachmentid(0);
						Attachment created = attachmentRepository.save(attachment);
						userLogList.add(attachment.getUserlog());
						BeanUtils.copyProperties(created, attachmentResource);
				String attachmetString = null;
				try {
					attachmetString = GotracratUtility.decompress(created.getAttachment());
				} catch (IOException e) {
					throw new InternalServerException(e);
				}
				attachmentResource.setAttachmentFile(attachmetString);
					} else {
						attachmentResource = null;
					}
		});
		saveUserLogList(userLogList);
		return attachmentResourceList;
	}

	public void saveUserLogList(List<UserLog> userLogList) {
		userLogRepository.saveAll(userLogList);
	}

	@Override
	public List<AllAttachmentDTO> getAllAttachments(String moduleType, int entityId) {
		int entityTypeId = findEntityTypeId(entityId, moduleType);
		if (entityTypeId != 0) {
			return attachmentRepository.getAllAttachments(entityId, entityTypeId);
		}
		return Collections.emptyList();
	}

}
