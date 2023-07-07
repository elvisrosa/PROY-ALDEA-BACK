/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aldea.cristo.persistencia.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author elvis
 */
public class UserRoleId implements Serializable {
       
    private String username;
    private String role;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserRoleId other = (UserRoleId) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    

    
    
    
}
