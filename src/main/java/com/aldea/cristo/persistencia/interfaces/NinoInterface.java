package com.aldea.cristo.persistencia.interfaces;
import com.aldea.cristo.persistencia.entities.NinoEntity;
import java.util.List;

public interface NinoInterface {
    
    List<NinoEntity> findAll();
    NinoEntity findBId(String cedula);
    void delete(String cedula);
    NinoEntity save(NinoEntity e);
}
