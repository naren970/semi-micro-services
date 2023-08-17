package com.gotracrat.managelocation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MasterSearchWithAttributesRequestDTO {

    private String tag;
    private String attributesXml;
    private String locationName;
    private List<AttributeDTO> attributes;
    private Integer page;
    private Integer size;


} 
