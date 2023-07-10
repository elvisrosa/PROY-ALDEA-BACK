package com.aldea.cristo.servicios;

import com.aldea.cristo.persistencia.entities.NinoEntity;
import com.aldea.cristo.persistencia.interfaces.NinoInterface;
import com.aldea.cristo.persistencia.repository.NinoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NinoService implements NinoInterface {

    @Autowired
    private NinoRepository service;

    @Override
    @Transactional(readOnly = true)
    public List<NinoEntity> findAll() {
        return (List<NinoEntity>) service.findAll();
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
        /*NinoEntity niño;
        if(e.getCedula()!=null){
            niño = service.save(e);
        }else{
          niño = service.save(e);
        }           
        return niño;
    }
         */

    }
