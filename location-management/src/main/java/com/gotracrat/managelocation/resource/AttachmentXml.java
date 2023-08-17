package com.gotracrat.managelocation.resource;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


/**
 * AttachmentXml.
 *
 * @author anudeep
 * @since 22-12-2021
 */
@XmlRootElement(name = "Attachments")
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class AttachmentXml {

    @XmlElement(name = "AttachmentName")
    private List<AttachmentNameXml> attachmentsList;
}
