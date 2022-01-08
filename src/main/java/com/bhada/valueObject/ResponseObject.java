package com.bhada.valueObject;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObject implements Serializable {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 9103868743298825774L;
	
    private CrudEnum requestType;
    private Boolean success;
    private ArrayList<ErrorData> detailErrors;
    private Object object;
    private int statusCode;
    private String statusText;
}