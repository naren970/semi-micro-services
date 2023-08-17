package com.gotracrat.attributesandtypes.controller.resource;

import java.util.List;

public class AttachmentAddDTO {
private List<AttachmentResource> attachmentResourceList;
private AttachmentUserLogDTO attachmentUserLogDTO;


public List<AttachmentResource> getAttachmentResourceList() {
	return attachmentResourceList;
}
public void setAttachmentResourceList(List<AttachmentResource> attachmentResourceList) {
	this.attachmentResourceList = attachmentResourceList;
}
public AttachmentUserLogDTO getAttachmentUserLogDTO() {
	return attachmentUserLogDTO;
}
public void setAttachmentUserLogDTO(AttachmentUserLogDTO attachmentUserLogDTO) {
	this.attachmentUserLogDTO = attachmentUserLogDTO;
}



}
