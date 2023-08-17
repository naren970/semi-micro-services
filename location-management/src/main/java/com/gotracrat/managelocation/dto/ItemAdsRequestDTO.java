package com.gotracrat.managelocation.dto;

import com.gotracrat.managelocation.resource.AttributeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Dto for Item advance search Request
 *
 * @author - manikanta
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemAdsRequestDTO {
    private Integer companyId;
    private String name;
    private String tag;
    private String locationName;
    private Integer statusId;
    private Integer locationId;
    private Integer typeId;
    private Integer maxHitCount;
    private boolean isOwnerAdmin;
    private String userId;
    private List<AttributeName> attributeNameList;
}
