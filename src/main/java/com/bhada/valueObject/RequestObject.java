package com.bhada.valueObject;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestObject implements Serializable {
	
	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = -8777388236548113487L;
	
    private String version;
    private CrudEnum requestType;
    private Integer pageNo;
    private Integer pageSize;
    private String userData;
    private Object object;
}
