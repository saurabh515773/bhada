package com.bhada.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhada.entity.UniversalAddress;

@Repository
public interface UniversalAddressRepository extends JpaRepository<UniversalAddress, Integer>{

}
