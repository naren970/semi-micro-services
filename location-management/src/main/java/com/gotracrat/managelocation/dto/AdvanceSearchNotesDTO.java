package com.gotracrat.managelocation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gotracrat.managelocation.resource.ItemChangeLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Hateoas resource associated with Journal.
 *
 * @author prabhakar
 * @since
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdvanceSearchNotesDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String tag;
    private Integer itemId;
    private String typeName;
    private Integer journalId;
    private String entry;
    private Date enteredOn;
    private String enteredBy;
    private String jobNumber;
    private String poNumber;
    private Integer rank;
    @JsonIgnore
    private Integer locationId;
}
