package com.bhada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhada.entity.Property;
import com.bhada.service.IPropertyService;
import com.bhada.valueObject.RequestObject;
import com.bhada.valueObject.ResponseObject;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private IPropertyService iPropertyService;

	@PostMapping(path = "/addproperty", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseObject> addProperty(@RequestBody RequestObject requestObj){
		try {
			Property property = objectMapper.convertValue(requestObj.getObject(), Property.class);
			Property addedProperty = iPropertyService.addProperty(property, requestObj.getUserData());			
						
			ResponseObject responseObject = new ResponseObject();
			if (addedProperty !=  null) {
				responseObject.setObject(addedProperty);
				responseObject.setRequestType(requestObj.getRequestType());
				responseObject.setStatusCode(200);
				responseObject.setStatusText("Success");
				return new ResponseEntity<ResponseObject>(HttpStatus.ACCEPTED).ok(responseObject);
			} else {				
				responseObject.setRequestType(requestObj.getRequestType());
				responseObject.setStatusCode(404);
				responseObject.setStatusText("Failure");
				return new ResponseEntity<ResponseObject>(HttpStatus.CONFLICT).ok(responseObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseObject>(HttpStatus.INTERNAL_SERVER_ERROR).ok(null);
		}
	}
}
