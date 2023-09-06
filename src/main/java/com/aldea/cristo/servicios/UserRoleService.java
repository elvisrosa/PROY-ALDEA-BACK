/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aldea.cristo.servicios;

import com.aldea.cristo.persistencia.entities.UserRolEntity;
import com.aldea.cristo.persistencia.interfaces.InterfazGenerica;
import com.aldea.cristo.persistencia.repository.UserRolRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author elvis
 */
@Service("servicioRol")
public class UserRoleService implements InterfazGenerica<UserRolEntity, String> {

    @Autowired
    private UserRolRepository respository_rol;

    @Override
    @Transactional
    public void delete(String cedula) {
        respository_rol.deleteByUsername(cedula);
    }

    //NO SE ESTA USANDO -- PERO FUNCIOAN BIEN
    @Transactional
    public boolean eliminar(String username) {
        try {
            respository_rol.deleteByUsername(username);
            return true;
        } catch (Exception e) {
            System.out.println("error " +e.getMessage());
            return false;
        }
    }

    @Override
    public UserRolEntity save(UserRolEntity e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserRolEntity> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserRolEntity findBId(String cedula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
