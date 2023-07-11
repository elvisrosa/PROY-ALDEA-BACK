package com.aldea.cristo.servicios;

import com.aldea.cristo.persistencia.entities.CasaEntity;
import com.aldea.cristo.persistencia.interfaces.InterfazGenerica;
import com.aldea.cristo.persistencia.repository.CasaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("servicioCasa")
public class CasaService implements InterfazGenerica<CasaEntity, Integer>{

    @Autowired
    private CasaRepository repository;
    
    @Override
    public List<CasaEntity> findAll() {
        return (List<CasaEntity>) repository.findAll();
    }

    @Override
    public CasaEntity findBId(Integer cedula) {
        return repository.findById(cedula).orElse(null);
    }

    @Override
    public void delete(Integer cedula) {
       repository.deleteById(cedula);
    }

    @Override
    public CasaEntity save(CasaEntity casa) {
        return repository.save(casa);
    }
    
}
