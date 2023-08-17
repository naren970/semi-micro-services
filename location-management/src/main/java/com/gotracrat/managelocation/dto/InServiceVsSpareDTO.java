package com.gotracrat.managelocation.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/*
 * DTO associated with InserviceVsSpare matching results.
 * @author manikanta
 * @since 2020-01-11
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InServiceVsSpareDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer itemId;
    private String typeName;
    private String tag;
    private String hp;
    private String rpm;
    private String frame;
    private List<SparesDTO> spares;

}
