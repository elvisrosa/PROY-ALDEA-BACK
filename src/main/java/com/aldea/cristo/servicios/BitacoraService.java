
package com.aldea.cristo.servicios;

import com.aldea.cristo.persistencia.entities.BitacoraEntity;
import com.aldea.cristo.persistencia.interfaces.InterfazGenerica;
import com.aldea.cristo.persistencia.repository.BitacoraRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author elvis
 */
@Service("bitacoraService")
public class BitacoraService implements InterfazGenerica<BitacoraEntity, Integer>{

    @Autowired
    private BitacoraRepository repository;
    
    @Override
    public List<BitacoraEntity> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BitacoraEntity findBId(Integer cedula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer cedula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BitacoraEntity save(BitacoraEntity e) {
        return repository.save(e);
    }
    
}
