/*
// * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aldea.cristo.persistencia.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;


//@Entity
//@Table(name = "fichamedica")
public class FichaMedicaEntity 
{
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ficha_medica", nullable = false)
    private Integer idFichaMedica;

    @Column(name = "fecha")
    private Timestamp fecha;

    @Column(name = "estatura")
    private String estatura;

    @Column(name = "peso")
    private float peso;

    @Column(name = "descripcion_diagnostico")
    private String descripcionDiagnostico;

    @Column(name = "descripcion_tratamiento")
    private String descripcionTratamiento;

    @Column(name = "medicamentos")
    private String medicamentos;

    @Column(name = "nombre_doctor")
    private String nombreDoctor;

    @Column(name = "tipo_sangre")
    private String tipoSangre;

    @ManyToOne
    @JoinColumn(name = "id_niño")
    private NinoEntity nino;

    public FichaMedicaEntity() {
    }

    public FichaMedicaEntity(Integer idFichaMedica, Timestamp fecha, String estatura, float peso, String descripcionDiagnostico, String descripcionTratamiento, String medicamentos, String nombreDoctor, String tipoSangre, NinoEntity nino) {
        this.idFichaMedica = idFichaMedica;
        this.fecha = fecha;
        this.estatura = estatura;
        this.peso = peso;
        this.descripcionDiagnostico = descripcionDiagnostico;
        this.descripcionTratamiento = descripcionTratamiento;
        this.medicamentos = medicamentos;
        this.nombreDoctor = nombreDoctor;
        this.tipoSangre = tipoSangre;
        this.nino = nino;
    }

    public Integer getIdFichaMedica() {
        return idFichaMedica;
    }

    public void setIdFichaMedica(Integer idFichaMedica) {
        this.idFichaMedica = idFichaMedica;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getDescripcionDiagnostico() {
        return descripcionDiagnostico;
    }

    public void setDescripcionDiagnostico(String descripcionDiagnostico) {
        this.descripcionDiagnostico = descripcionDiagnostico;
    }

    public String getDescripcionTratamiento() {
        return descripcionTratamiento;
    }

    public void setDescripcionTratamiento(String descripcionTratamiento) {
        this.descripcionTratamiento = descripcionTratamiento;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
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
        hash += (idFichaMedica != null ? idFichaMedica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
       
        if (!(object instanceof FichaMedicaEntity)) {
            return false;
        }
        FichaMedicaEntity other = (FichaMedicaEntity) object;
        if ((this.idFichaMedica == null && other.idFichaMedica != null) || (this.idFichaMedica != null && !this.idFichaMedica.equals(other.idFichaMedica))) {
            return false;
        }
        return true;
    }

    
}