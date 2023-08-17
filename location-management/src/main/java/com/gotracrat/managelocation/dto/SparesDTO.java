package com.gotracrat.managelocation.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/*
 * DTO associated with spares corresponding to Inservice Motors .
 * @author manikanta
 * @since 2020-01-12
 */

@Getter
@Setter
public class SparesDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	 private String itemId;
    private String tag;
   
}
