package com.bhada.services.impl;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhada.entity.Owner;
import com.bhada.entity.Property;
import com.bhada.repository.OwnerRepository;
import com.bhada.repository.PropertyRepository;
import com.bhada.service.IPropertyService;

@Service("PropertyServiceImpl")
public class PropertyServiceImpl implements IPropertyService {

	@Autowired
	PropertyRepository propertyRepository;

	@Autowired
	OwnerRepository ownerRepository;

	@Override
	public Property addProperty(Property property, String userData) {
		try {
			Optional<Owner> owner;
			if (userData != null  && !userData.trim().isEmpty()) {
				owner = ownerRepository.findByEmail(userData);
				if (owner.isPresent()) {
					property.setPropertyRegisteredDateTime(LocalDateTime.now());
					property.setPropertyExpiryDateTime((LocalDateTime.now()).plusYears(5));
					
					ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
				    ObjectOutputStream out = new ObjectOutputStream(byteOut);
				    out.writeObject(property.getAmenities());
				    property.setAmenities(byteOut.toByteArray());
				    
				    ByteArrayOutputStream byteOut1 = new ByteArrayOutputStream();
				    ObjectOutputStream out1 = new ObjectOutputStream(byteOut1);
				    out1.writeObject(property.getRules());
				    property.setRules(byteOut1.toByteArray());
						
				    property.setOwner(owner.get());
					propertyRepository.save(property);
					
					
					return property;
				}
			}
			// TO DO
			// to add property on basis of token
			else {

			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
