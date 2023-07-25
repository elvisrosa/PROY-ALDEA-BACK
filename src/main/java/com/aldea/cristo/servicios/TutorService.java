/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aldea.cristo.servicios;

import com.aldea.cristo.persistencia.entities.TutoraEntity;
import com.aldea.cristo.persistencia.interfaces.InterfazGenerica;
import com.aldea.cristo.persistencia.repository.TutorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("servicioTutor")
public class TutorService implements InterfazGenerica<TutoraEntity, Integer>{
    
    @Autowired
    private TutorRepository tutorRepository;

    @Override
    public List<TutoraEntity> findAll() {
        return (List<TutoraEntity>) tutorRepository.findAll();
    }

    @Override
    public TutoraEntity findBId(Integer cedula) {
        return tutorRepository.findById(cedula).orElse(null);
    }

    @Override
    public void delete(Integer cedula) {
        tutorRepository.deleteById(cedula);
    }

    @Override
    public TutoraEntity save(TutoraEntity e) {
        return tutorRepository.save(e);
    }
    
    @Transactional
    public List<TutoraEntity> finByIds(List<Integer> ids)
    {
        return (List<TutoraEntity>) tutorRepository.findAllById(ids);
    }
    
    
}
