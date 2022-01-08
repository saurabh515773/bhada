package com.bhada.services.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhada.entity.Owner;
import com.bhada.repository.OwnerRepository;

@Service("EmailMobileValidation")
public class EmailMobileValidation {
	
	@Autowired
	OwnerRepository ownerRepository;

	public boolean isValidEmailId(Owner owner) {
		String emailRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if(!isDuplicateEmail(owner)){
			if(owner.getEmail().matches(emailRegex)) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

	private boolean isDuplicateEmail(Owner owner) {
		if (owner != null && owner.getEmail() != null 
				&& ownerRepository.findByEmail(owner.getEmail()).isPresent())
			return true;
		return false;

	}

	public boolean isDuplicateMobileNumber(Owner owner) {
		if(owner != null && owner.getMobileNumber() != null 
				&& ownerRepository.findByMobileNumber(owner.getMobileNumber()).isPresent())
			return true;
		return false;
	}
}
