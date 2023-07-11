package com.aldea.cristo.persistencia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "casa")
public class CasaEntity {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_casa", nullable = false)
    private Integer idCasa;

    @Column(name = "numero_casa")
    private String numeroCasa;

    @Column(length = 10)
    private String telefono;

    private String direccion;
    
    @OneToMany(mappedBy = "casa", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<NinoEntity> niños;


    public CasaEntity() {
    }
    
    public CasaEntity(Integer idCasa, String numeroCasa, String telefono, String direccion) {
        this.idCasa = idCasa;
        this.numeroCasa = numeroCasa;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Integer getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(Integer idCasa) {
        this.idCasa = idCasa;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
   
     public List<NinoEntity> getNiños() {
        return niños;
    }

    public void setNiños(List<NinoEntity> niños) {
        this.niños = niños;
    }
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCasa != null ? idCasa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof CasaEntity)) {
            return false;
        }
        CasaEntity other = (CasaEntity) object;
        if ((this.idCasa == null && other.idCasa != null) || (this.idCasa != null && !this.idCasa.equals(other.idCasa))) {
            return false;
        }
        return true;
    }

    
}