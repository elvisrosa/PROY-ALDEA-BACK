/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aldea.cristo.persistencia.repository;

import com.aldea.cristo.persistencia.entities.TutoraEntity;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author elvis
 */
public interface TutorRepository extends CrudRepository<TutoraEntity, Integer>{
    
}
