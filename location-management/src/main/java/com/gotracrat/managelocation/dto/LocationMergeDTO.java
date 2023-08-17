package com.gotracrat.managelocation.dto;

public class LocationMergeDTO {

    private Integer newLocationId;
    private String newLocationName;
    private Integer OldLocationId;

    public Integer getNewLocationId() {
        return newLocationId;
    }

    public void setNewLocationId(Integer newLocationId) {
        this.newLocationId = newLocationId;
    }

    public String getNewLocationName() {
        return newLocationName;
    }

    public void setNewLocationName(String newLocationName) {
        this.newLocationName = newLocationName;
    }

    public Integer getOldLocationId() {
        return OldLocationId;
    }

    public void setOldLocationId(Integer oldLocationId) {
        OldLocationId = oldLocationId;
    }


}
