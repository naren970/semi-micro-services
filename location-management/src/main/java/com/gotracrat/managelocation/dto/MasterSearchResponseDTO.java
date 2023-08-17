
package com.gotracrat.managelocation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gotracrat.managelocation.resource.AttributeNameXml;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MasterSearchResponseDTO {

    private String tag;
    private Integer itemId;
    private Integer locationId;
    private Integer companyId;
    private String companyName;
    private String name;
    private Integer typeId;
    private String typeName;
    private String locationName;
    private String locationPath;
    private String status;
    private Integer rank;
    @JsonIgnore
    private String attributesXml;
    private List<AttributeNameXml> attributeNameList;

    public MasterSearchResponseDTO() {
        super();
    }

    public MasterSearchResponseDTO(String tag, Integer itemId, String companyName, String typeName, String attributesXml) {
        super();
        this.tag = tag;
        this.itemId = itemId;
        this.companyName = companyName;
        this.typeName = typeName;
        this.attributesXml = attributesXml;
    }
}
