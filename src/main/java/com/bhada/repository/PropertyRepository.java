package com.bhada.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhada.entity.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer>{

}
 