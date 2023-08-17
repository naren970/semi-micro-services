package com.gotracrat.managelocation.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class RecentItemsResource implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer itemId;
    private Integer typeId;
    private String tag;
    private String name;
    private String status;
    private Date dateAdded;
    private String location;
    private String typeName;
    @JsonIgnore
	private String attributeXml;
    private List<AttributeNameXml> attributeList;
    private	Map<String,List<String>> attributeNames;
    private Integer rank;
}
