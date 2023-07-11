package com.aldea.cristo.servicios;
import com.aldea.cristo.persistencia.entities.BautismoEntity;
import com.aldea.cristo.persistencia.repository.BautizoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aldea.cristo.persistencia.interfaces.InterfazGenerica;

@Service("servicioBautizo")
public class BautizoService  implements InterfazGenerica<BautismoEntity, Integer>{

    @Autowired
    private BautizoRepository service;
    
    @Override
    public List<BautismoEntity> findAll() {
        return (List<BautismoEntity>) service.findAll();
    }

    @Override
    public BautismoEntity findBId(Integer id) {
        return (BautismoEntity) service.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        service.deleteById(id);
    }

    @Override
    public BautismoEntity save(BautismoEntity e) {
        return service.save(e);
    }
    
}
