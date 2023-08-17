package com.gotracrat.managelocation.resource;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.gotracrat.managelocation.dto.SparesDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "Spares")
@XmlAccessorType(XmlAccessType.FIELD)
public class SparesXml {


    @XmlElement(name = "Item")
    List<SparesDTO> spares;
}
