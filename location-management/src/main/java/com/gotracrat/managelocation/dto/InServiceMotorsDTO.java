package com.gotracrat.managelocation.dto;

import lombok.*;

import java.io.Serializable;

/*
 * DTO associated with Inservice(which dont have any spare motors)
 *  or Spare Motors which dont have any Inservice motors
 * @author manikanta
 * @since 2020-01-12
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InServiceMotorsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer itemId;
	private String tag;
	private String typeName;
	private String hp;
	private String rpm;
	private String frame;

}
