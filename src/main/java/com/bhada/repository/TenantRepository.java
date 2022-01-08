package com.bhada.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhada.entity.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer>{

}
