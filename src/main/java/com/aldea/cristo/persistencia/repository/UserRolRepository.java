/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aldea.cristo.persistencia.repository;

import com.aldea.cristo.persistencia.entities.UserRolEntity;
import com.aldea.cristo.persistencia.entities.UserRoleId;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author elvis
 */
public interface UserRolRepository extends CrudRepository<UserRolEntity, UserRoleId> {

    //@Query("SELECT ur FROM UserRolEntity ur WHERE ur.username = :username")
    //List<UserRolEntity> findByUsername(String username);
    
     List<UserRolEntity> findByUsername(String username);
     void deleteByUsername(String username);
}
