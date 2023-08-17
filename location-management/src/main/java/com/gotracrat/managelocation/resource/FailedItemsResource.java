package com.gotracrat.managelocation.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class FailedItemsResource {

    private String tag;
    private Integer itemId;
    private Integer companyId;
    private String companyName;
    private String typeName;
    private String locationName;
    private String name;

    private Integer rank;
    @JsonIgnore
    private String attributesXml;
    private List<AttributeNameXml> attributeNameList;
    private String status;
}
