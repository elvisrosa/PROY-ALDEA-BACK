/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aldea.cristo.persistencia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author elvis
 */
@Getter
@Setter 
@NoArgsConstructor
@Entity
@Table(name = "user_role")
@IdClass(UserRoleId.class)
public class UserRolEntity {

    @Id
    @Column(nullable = false, length = 20)
    @JsonIgnore
    private String username;

    @Id
    @Column(nullable = false, length = 20)
    private String role;

    @Column(name = "granted_date", columnDefinition = "TIMESTAMP")
    private Timestamp grantedDate;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    @JsonIgnore
    private UserEntity user;

    @PrePersist
    protected void onCreate() {
        grantedDate = new Timestamp(System.currentTimeMillis());
    }

}
