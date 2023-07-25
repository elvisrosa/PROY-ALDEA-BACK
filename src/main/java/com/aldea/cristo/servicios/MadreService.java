/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aldea.cristo.servicios;

import com.aldea.cristo.persistencia.entities.MadreEntity;
import com.aldea.cristo.persistencia.interfaces.InterfazGenerica;
import com.aldea.cristo.persistencia.repository.MadreRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author elvis
 */

@Service("servicioMadre")
public class MadreService implements InterfazGenerica<MadreEntity, String>{
    
    @Autowired
    private MadreRepository repository;

    @Override
    public List<MadreEntity> findAll() {
       return (List<MadreEntity>) repository.findAll();
    }

    @Override
    public MadreEntity findBId(String cedula) {
        return repository.findById(cedula).orElse(null);
    }

    @Override
    public void delete(String cedula) {
        repository.deleteById(cedula);
    }

    @Override
    public MadreEntity save(MadreEntity e) {
       return repository.save(e);
    }
    
}
