package com.bhada.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhada.entity.Owner;
import com.bhada.repository.OwnerRepository;
import com.bhada.services.validators.EmailMobileValidation;
import com.bhada.valueObject.AppConstants;
import com.bhada.valueObject.ErrorData;
import com.bhada.valueObject.RequestObject;
import com.bhada.valueObject.ResponseObject;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private EmailMobileValidation validation;

	@Autowired
	private ObjectMapper objectMapper;

	@PostMapping(path = "/addOwner", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseObject> listAll(@RequestBody RequestObject requestObj){
		try {
			Owner owner = objectMapper.convertValue(requestObj.getObject(), Owner.class);
			ResponseObject responseObject = new ResponseObject();
			if (validation.isValidEmailId(owner) && !validation.isDuplicateMobileNumber(owner)) {
				ownerRepository.save(owner);
				responseObject.setObject(owner);
				responseObject.setRequestType(requestObj.getRequestType());
				responseObject.setStatusCode(200);
				responseObject.setStatusText("Success");
				return new ResponseEntity<ResponseObject>(HttpStatus.ACCEPTED).ok(responseObject);
			} else {
				ArrayList<ErrorData> errorList = new ArrayList<>();
				ErrorData errorData1 = new ErrorData("email", owner.getEmail(), AppConstants.INVALID_EMAIL);
				ErrorData errorData2 = new ErrorData("mobileNumber", owner.getMobileNumber(),
						AppConstants.INVALID_MOBILE);
				errorList.add(errorData1);
				errorList.add(errorData2);
				responseObject.setObject(owner);
				responseObject.setRequestType(requestObj.getRequestType());
				responseObject.setStatusCode(409);
				responseObject.setStatusText("Failure");
				responseObject.setDetailErrors(errorList);
				return new ResponseEntity<ResponseObject>(HttpStatus.CONFLICT).ok(responseObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseObject>(HttpStatus.INTERNAL_SERVER_ERROR).ok(null);
	}
}