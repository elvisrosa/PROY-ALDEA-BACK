package com.aldea.cristo.persistencia.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "nino")
public class NinoEntity {

    @Id
    @Column(name = "cedula", nullable = false, length = 10)
    private String cedula;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(name = "fecha_nacimiento")
    private Timestamp fechaNacimiento;

    /*@PrePersist
    public void setFechaRegistro() {
        fechaNacimiento = new Timestamp();
    }*/
    @Column(name = "lugar_nacimiento", nullable = false)
    private String lugarNacimiento;

    @Column(nullable = false)
    private Integer edad;

    @Column(nullable = false)
    private String sexo;
    
    private Boolean ausente;

    //RELACION ONETOONE CON BAUTIZO
    //@JsonIgnore
    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bautismo")
    private BautismoEntity bautizo;
    
    //RELACION CON CASA 1 CASA VAROS NIÑOS
    @ManyToOne()
    @JoinColumn(name = "id_casa")
    private CasaEntity casa;
    
    //RELACION CON PADRE MANYTOONE
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_padre")
    private PadreEntity padre;

    public NinoEntity() {
    }

    public PadreEntity getPadre() {
        return padre;
    }

    public void setPadre(PadreEntity padre) {
        this.padre = padre;
    }
    
    public BautismoEntity getBautizo() {
        return bautizo;
    }

    public void setBautizo(BautismoEntity bautizo) {
        this.bautizo = bautizo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Timestamp getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Timestamp fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public CasaEntity getCasa() {
        return casa;
    }

    public void setCasa(CasaEntity casa) {
        this.casa = casa;
    }

    public Boolean getAusente() {
        return ausente;
    }

    public void setAusente(Boolean ausente) {
        this.ausente = ausente;
    }

    @Override
    public String toString() {
        return "NinoEntity{" + "cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento + ", lugarNacimiento=" + lugarNacimiento + ", edad=" + edad + ", sexo=" + sexo + ", estado=" + ausente + ", bautizo=" + bautizo + ", casa=" + casa + ", padre=" + padre + '}';
    }

    

    

}
