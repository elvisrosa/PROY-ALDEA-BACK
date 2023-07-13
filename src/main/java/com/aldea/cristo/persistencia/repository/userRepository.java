package com.aldea.cristo.persistencia.repository;
import com.aldea.cristo.persistencia.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
public interface userRepository extends CrudRepository<UserEntity, String>{}
