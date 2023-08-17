package com.gotracrat.managelocation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gotracrat.managelocation.resource.MasterSearchAttributesXml;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MasterSearchWithAttributesResponseDTO {

    private String tag;
    private Integer itemId;
    private Integer companyId;
    private String companyName;
    private String typeName;
    private String locationName;
    private Integer totalRows;
    private String name;
    @JsonIgnore
    private String attributesXml;
    private List<MasterSearchAttributesXml> attributes;
    private String status;
} 
