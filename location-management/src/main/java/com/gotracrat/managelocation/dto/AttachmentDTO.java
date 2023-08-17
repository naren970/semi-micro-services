package com.gotracrat.managelocation.dto;

public class AttachmentDTO {

	
	private Integer EntityId;
	private Integer AttachmentID;
	private String FileName;
	private boolean isNew;
	private String contenttype;
	private String attachmentFile;
	
	public Integer getEntityId() {
		return EntityId;
	}
	public void setEntityId(Integer entityId) {
		EntityId = entityId;
	}
	public Integer getAttachmentID() {
		return AttachmentID;
	}
	public void setAttachmentID(Integer attachmentID) {
		AttachmentID = attachmentID;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public boolean isNew() {
		return isNew;
	}
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	public String getAttachmentFile() {
		return attachmentFile;
	}
	public void setAttachmentFile(String attachmentFile) {
		this.attachmentFile = attachmentFile;
	}
	public String getContenttype() {
		return contenttype;
	}
	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}
	
	}
	
	

