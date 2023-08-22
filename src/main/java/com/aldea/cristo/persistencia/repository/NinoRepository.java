/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aldea.cristo.persistencia.repository;

import com.aldea.cristo.persistencia.entities.NinoEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author elvis
 */
public interface NinoRepository extends CrudRepository<NinoEntity, String> {

    @Query("SELECT n FROM NinoEntity n Where n.casa.idCasa=:idCasa")
    List<NinoEntity> findNinoByIdCasa(@Param("idCasa") Integer idCasa);

}
