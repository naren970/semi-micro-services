package com.gotracrat.managecompany.resource;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
/**
 * since 2020-03-10
 * @author shirisha
 */
@Setter
@Getter
public class ManualResource implements Serializable {


	private static final long serialVersionUID = 1L;
	private Integer manualid;
	private String filename;
	private String contenttype;
	private String description;
	private Byte manual;
	private String manualFile;

	public ManualResource() {
		
	}
}
