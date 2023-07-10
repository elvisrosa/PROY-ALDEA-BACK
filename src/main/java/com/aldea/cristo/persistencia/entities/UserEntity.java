package com.aldea.cristo.persistencia.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuario")
public class UserEntity {

    @Id
    @Column(nullable = false, length = 20)
    private String username;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    private String nombre;

    @Column(name = "bloqueado")
    private Boolean locked;

    @Column(name = "deshabilitado")
    private Boolean disabled;
     
    @Column(name = "contrasena", nullable = false, length = 200)
    private String password;
    
    private String correo;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRolEntity> roles;
      
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    

    @Override
    public String toString() {

        return "UserEntity{" + "username=" + username + ", nombre=" + nombre + ", locked=" + locked + ", disabled=" + disabled + ", password=" + password + ", correo=" + correo + ", roles=" + roles + '}';
    }

    
 
    
    
    
    
    

}