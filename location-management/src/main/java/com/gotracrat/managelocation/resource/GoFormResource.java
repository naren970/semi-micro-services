package com.gotracrat.managelocation.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoFormResource {
    private String EventType;
    private String EntityId;
    private String Timestamp;
    private GoFormItem Item;
}
