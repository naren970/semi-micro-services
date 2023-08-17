package com.gotracrat.attributesandtypes.exception;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionResponse implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	
	public ExceptionResponse(String errorMessage,List<String> details) {
		super();
		this.errorMessage = errorMessage;
		this.details = details;
	}
	
	
	private String errorMessage;
	private List<String> details;
	
	
}