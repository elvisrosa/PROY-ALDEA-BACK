package com.aldea.cristo.persistencia.interfaces;
import java.util.List;

public interface InterfazGenerica<T, M> {
    
    List<T> findAll();
    T findBId(M cedula);
    void delete(M cedula);
    T save(T e);
}
