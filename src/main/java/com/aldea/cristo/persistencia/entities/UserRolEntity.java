/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aldea.cristo.persistencia.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;
/**
 *
 * @author elvis
 */
@Entity
@Table(name = "user_role")
@IdClass(UserRoleId.class)
public class UserRolEntity {
    
    
    
     @Id
    @Column(nullable = false, length = 20)
    private String username;

    @Id
    @Column(nullable = false, length = 20)
    private String role;

    @Column(name = "granted_date", nullable = false, columnDefinition = "TIMESTAMP")
    private Timestamp grantedDate;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private UserEntity user; 

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getGrantedDate() {
        return grantedDate;
    }

    public void setGrantedDate(Timestamp grantedDate) {
        this.grantedDate = grantedDate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
    
    

}
