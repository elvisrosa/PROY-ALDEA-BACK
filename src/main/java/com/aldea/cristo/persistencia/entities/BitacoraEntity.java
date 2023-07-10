package com.aldea.cristo.persistencia.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;


//@Entity
//@Table(name = "bitacora")
public class BitacoraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bitacora", nullable = false)
    private Integer idBitacora;

    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_niño")
    private NinoEntity nino;

    public BitacoraEntity() {
    }

    public BitacoraEntity(Integer idBitacora, Timestamp fechaRegistro, String descripcion, NinoEntity nino) {
        this.idBitacora = idBitacora;
        this.fechaRegistro = fechaRegistro;
        this.descripcion = descripcion;
        this.nino = nino;
    }

    public Integer getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public NinoEntity getNino() {
        return nino;
    }

    public void setNino(NinoEntity nino) {
        this.nino = nino;
    }
    
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBitacora != null ? idBitacora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
       
        if (!(object instanceof BitacoraEntity)) {
            return false;
        }
        BitacoraEntity other = (BitacoraEntity) object;
        if ((this.idBitacora == null && other.idBitacora != null) || (this.idBitacora != null && !this.idBitacora.equals(other.idBitacora))) {
            return false;
        }
        return true;
    }
    
}