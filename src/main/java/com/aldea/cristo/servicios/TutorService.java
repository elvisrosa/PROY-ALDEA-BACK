/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aldea.cristo.servicios;

import com.aldea.cristo.persistencia.entities.CasaEntity;
import com.aldea.cristo.persistencia.entities.TutoraEntity;
import com.aldea.cristo.persistencia.interfaces.InterfazGenerica;
import com.aldea.cristo.persistencia.repository.CasaRepository;
import com.aldea.cristo.persistencia.repository.TutorRepository;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("servicioTutor")
public class TutorService implements InterfazGenerica<TutoraEntity, Integer>{
    
    @Autowired
    private TutorRepository tutorRepository;
    
    @Autowired
    private CasaRepository casaRepository;

    @Override
    //@EntityGraph(attributePaths = "casa")
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
    
    public TutoraEntity asignarTutorToCurso(Integer idTutor, Integer idCasa){
        Set<CasaEntity> casas=null;
        TutoraEntity tutora = tutorRepository.findById(idTutor).get();
        CasaEntity casae = casaRepository.findById(idCasa).get();
        
        casas = tutora.getCasas();
        casas.add(casae);
        tutora.setCasas(casas);
        return tutorRepository.save(tutora);
    }
    
    
    public Set<CasaEntity> getCasasByTutorId(Integer tutorId) {
        return tutorRepository.findCasasByTutoraId(tutorId);
    }
    
    
     
    
}
