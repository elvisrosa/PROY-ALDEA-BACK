package com.aldea.cristo.persistencia.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "locked")
    private Boolean locked;

    @Column(name = "disabled")
    private Boolean disabled;

    
    @Column(name = "password", nullable = false, length = 200)
    private String password;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRolEntity> roles;
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRolEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRolEntity> roles) {
        this.roles = roles;
    }
    
    
    

}