/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aldea.cristo.servicios.dtos;

import java.util.List;

/**
 *
 * @author elvis
 */
public class ResponseUsuario {
    
    private String username;
    private String nombre;
    private String correo;
    private List<String> roles;

    public ResponseUsuario(String username, String nombre, String correo, List<String> roles) {
        this.username = username;
        this.nombre = nombre;
        this.correo = correo;
        this.roles = roles;
    }

    public ResponseUsuario() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    
    
    
}
