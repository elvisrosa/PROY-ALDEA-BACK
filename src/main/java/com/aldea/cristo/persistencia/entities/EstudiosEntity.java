package com.aldea.cristo.persistencia.entities;

import jakarta.persistence.*;

//@Entity
//@Table(name = "estudios")
public class EstudiosEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudios", nullable = false)
    private Integer idEstudios;

    @Column(name = "niveles")
    private String niveles;

    @Column(name = "ciclo")
    private String ciclo;

    @Column(name = "promedios")
    private float promedios;

    @ManyToOne
    @JoinColumn(name = "id_niño")
    private NinoEntity nino;

    public EstudiosEntity() {
    }

    public EstudiosEntity(Integer idEstudios, String niveles, String ciclo, float promedios, NinoEntity nino) {
        this.idEstudios = idEstudios;
        this.niveles = niveles;
        this.ciclo = ciclo;
        this.promedios = promedios;
        this.nino = nino;
    }

    public Integer getIdEstudios() {
        return idEstudios;
    }

    public void setIdEstudios(Integer idEstudios) {
        this.idEstudios = idEstudios;
    }

    public String getNiveles() {
        return niveles;
    }

    public void setNiveles(String niveles) {
        this.niveles = niveles;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public float getPromedios() {
        return promedios;
    }

    public void setPromedios(float promedios) {
        this.promedios = promedios;
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
        hash += (idEstudios != null ? idEstudios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof EstudiosEntity)) {
            return false;
        }
        EstudiosEntity other = (EstudiosEntity) object;
        if ((this.idEstudios == null && other.idEstudios != null) || (this.idEstudios != null && !this.idEstudios.equals(other.idEstudios))) {
            return false;
        }
        return true;
    }
}