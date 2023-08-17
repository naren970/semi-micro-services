package com.gotracrat.managelocation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gotracrat.managelocation.resource.AttributeNameXml;
import lombok.Data;

import java.util.List;

@Data
public class SearchResponseDTO {
    private Integer itemId;
    private String tag;
    private String status;
    private String typeName;
    private String locationName;
    private String locationPath;
    private Integer itemRank;
    @JsonIgnore
    private String attributesXml;
    private List<AttributeNameXml> attributeNameList;

    public SearchResponseDTO(Integer itemId, String tag, String status, String typeName, String locationName,
                             String locationPath, Integer itemRank) {
        super();
        this.itemId = itemId;
        this.tag = tag;
        this.status = status;
        this.typeName = typeName;
        this.locationName = locationName;
        this.locationPath = locationPath;
        this.itemRank = itemRank;
    }
}
