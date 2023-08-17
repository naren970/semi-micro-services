package com.gotracrat.managelocation.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gotracrat.managelocation.dto.RepairLogAttachmentDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class NotesResource implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer itemId;
    private Integer journalId;
    private String tag;
    private String typeName;
    private String title;
    private Date addedOn;
    private String details;
    private Integer rank;
    @JsonIgnore
    private String attachmentXml;
    private List<AttachmentNameXml> attachmentListFromXml;
    private List<RepairLogAttachmentDTO> attachmentList;
}
