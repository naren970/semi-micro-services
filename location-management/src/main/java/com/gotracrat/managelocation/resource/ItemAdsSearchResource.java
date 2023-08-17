package com.gotracrat.managelocation.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class  ItemAdsSearchResource {
    private String typeName;
    private String tag;
    private String locationName;
    private String locationPath;
    @JsonIgnore
    private Integer locationId;
    private Integer itemId;
    private String name;
    private String status;
    private Integer rank;
    @JsonIgnore
    private String attributesXml;
    private List<AttributeNameXml> attributeNameList;
}
