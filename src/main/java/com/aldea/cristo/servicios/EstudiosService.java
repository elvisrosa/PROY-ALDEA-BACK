package com.aldea.cristo.servicios;
import com.aldea.cristo.persistencia.entities.EstudiosEntity;
import com.aldea.cristo.persistencia.interfaces.InterfazGenerica;
import com.aldea.cristo.persistencia.repository.EstudiosRepository;
import com.aldea.cristo.persistencia.repository.NinoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("servicioEstudio")
public class EstudiosService implements InterfazGenerica<EstudiosEntity, Integer>{
    
    @Autowired
    private EstudiosRepository repository;
    
    @Autowired
    private NinoRepository repositorynino;

    @Override
    public List<EstudiosEntity> findAll() {
        return (List<EstudiosEntity>) repository.findAll();
    }

    @Override
    public EstudiosEntity findBId(Integer cedula) {
       return repository.findById(cedula).orElse(null);
    }

    @Override
    public void delete(Integer cedula) {
        repository.deleteById(cedula);
    }

    @Override
    public EstudiosEntity save(EstudiosEntity e) {  
        //NinoEntity ninoencontrado = repositorynino.findById(e.getNino().getCedula()).orElse(null);
        //System.out.println("nuno encontrado" .concat(ninoencontrado.toString()));
        //e.setNino(ninoencontrado);
        return repository.save(e);
    }
    
    
    
}
