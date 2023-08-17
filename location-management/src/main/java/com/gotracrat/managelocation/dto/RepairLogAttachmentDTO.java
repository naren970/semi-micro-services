package com.gotracrat.managelocation.dto;

public class RepairLogAttachmentDTO {
private Integer attachmentId;
private String fileName;
private boolean isNew;
private String contenttype;
private String attachmentFile;
private Integer entityId;
public Integer getAttachmentId() {
	return attachmentId;
}
public void setAttachmentId(Integer attachmentId) {
	this.attachmentId = attachmentId;
}
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public String getContenttype() {
	return contenttype;
}
public void setContenttype(String contenttype) {
	this.contenttype = contenttype;
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
public Integer getEntityId() {
	return entityId;
}
public void setEntityId(Integer entityId) {
	this.entityId = entityId;
}

}