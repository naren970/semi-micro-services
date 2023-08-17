package com.gotracrat.managelocation.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * AttachmentNameXml.
 *
 * @author anudeep
 * @since 22-12-2021
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentNameXml {

    @XmlElement(name = "AttachmentID")
    private String attachmentId;
    @XmlElement(name = "FileName")
    private String fileName;
    @XmlElement(name = "isNew")
    private String isNew;
}
