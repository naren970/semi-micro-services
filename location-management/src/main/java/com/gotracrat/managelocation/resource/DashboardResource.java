package com.gotracrat.managelocation.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResource implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer companyId;
    private Integer locationId;
    private List<RecentRepairResource> repairResource;
    private Map<String, List<RecentItemsResource>> recentItemResource;
    private List<NotesResource> notesResource;
    private Map<String, List<String>> attributeNameMap;
    private List<String> AnnouncementList;
}
