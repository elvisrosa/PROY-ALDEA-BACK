package com.aldea.cristo.servicios;

import com.aldea.cristo.persistencia.entities.NinoEntity;
import com.aldea.cristo.persistencia.repository.NinoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aldea.cristo.persistencia.interfaces.InterfazGenerica;

@Service("servicioNino")
public class NinoService implements InterfazGenerica<NinoEntity, String> {

    @Autowired
    private NinoRepository service;

    @Override
    @Transactional(readOnly = true)
    public List<NinoEntity> findAll() {
        return  (List<NinoEntity>) service.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public NinoEntity findBId(String cedula) {
        return service.findById(cedula).orElse(null);
    }

    @Override
    @Transactional()
    public void delete(String cedula) {
        service.deleteById(cedula);
    }

    @Override
    @Transactional()
    public NinoEntity save(NinoEntity e) {
        return service.save(e);
    }
    

}
