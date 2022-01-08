package com.bhada.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhada.entity.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer>{

	public Optional<Owner> findByEmail(String email);
	
	public Optional<Owner> findByMobileNumber(String mobileNumber);
}
