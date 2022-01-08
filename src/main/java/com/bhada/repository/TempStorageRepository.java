package com.bhada.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bhada.entity.TempStorage;

@Repository
public interface TempStorageRepository extends CrudRepository<TempStorage, String>{

}
