package com.aldea.cristo.persistencia.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "nino")
public class NinoEntity {

    /*@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nino")
    private Integer idNino;*/
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

    //RELACION ONETOONE CON BAUTIZO
    @OneToOne(mappedBy = "nino")
    private BautismoEntity bautizo;

    /*@ManyToOne
    @JoinColumn(name = "id_padre")
    private PadreEntity padre;

    @ManyToOne
    @JoinColumn(name = "id_madre")
    private MadreEntity madre;

    @ManyToOne
    @JoinColumn(name = "id")
    private UserEntity users;

    @ManyToOne
    @JoinColumn(name = "id_casa")
    private CasaEntity casa;

    @ManyToOne
    @JoinColumn(name = "id_bautismo")
    private BautismoEntity bautismo;

    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private GrupoEntity grupo;
    
    @OneToMany(mappedBy = "nino", fetch = FetchType.EAGER)
    private List<FichaMedicaEntity> fichasMedicas;

    @OneToMany(mappedBy = "nino", fetch = FetchType.EAGER)
    private List<BitacoraEntity> bitacoras;

    @OneToMany(mappedBy = "nino", fetch = FetchType.EAGER)
    private List<EstudiosEntity> estudios;
     */
    public NinoEntity() {
    }

    public NinoEntity(String cedula, String nombres, String apellidos, Timestamp fechaNacimiento, String lugarNacimiento, Integer edad, String sexo) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.lugarNacimiento = lugarNacimiento;
        this.edad = edad;
        this.sexo = sexo;
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

    @Override
    public String toString() {
        return "NinoEntity{" + " cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento + ", lugarNacimiento=" + lugarNacimiento + ", edad=" + edad + ", sexo=" + sexo + ", bautizo=" + bautizo + '}';
    }

}
