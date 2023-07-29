/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aldea.cristo.servicios.dtos;

import com.aldea.cristo.persistencia.entities.TutoraEntity;
import com.aldea.cristo.persistencia.entities.UserRolEntity;
import java.util.List;

/**
 *
 * @author elvis
 */
public class UserTutor {
    
    private String username;

    private Boolean locked;
  
    private Boolean disabled;
     
    private String password;
    
    private List<UserRolEntity> roles;
    
    private TutoraEntity tutor;
    
}
