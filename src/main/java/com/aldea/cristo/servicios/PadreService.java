package com.aldea.cristo.servicios;

import com.aldea.cristo.persistencia.entities.PadreEntity;
import com.aldea.cristo.persistencia.interfaces.InterfazGenerica;
import com.aldea.cristo.persistencia.repository.PadreRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("servicioPadre")
public class PadreService implements InterfazGenerica<PadreEntity, String>{

    
    @Autowired
    private PadreRepository padreRepository;
    
    @Override
    public List<PadreEntity> findAll() {
       return (List<PadreEntity>) padreRepository.findAll();
    }

    @Override
    public PadreEntity findBId(String cedula) {
       return padreRepository.findById(cedula).orElse(null);
    }

    @Override
    public void delete(String cedula) {
        padreRepository.deleteById(cedula);
    }

    @Override
    public PadreEntity save(PadreEntity padre) {
        return padreRepository.save(padre);
    }
    
}
