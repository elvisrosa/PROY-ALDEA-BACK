package com.aldea.cristo.persistencia.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
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
    
    
    //RELACION CON MADRE MANYTOONE
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_madre")
    private MadreEntity madre;

    @Override
    public String toString() {
        return "NinoEntity{" + "cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento + ", lugarNacimiento=" + lugarNacimiento + ", edad=" + edad + ", sexo=" + sexo + ", ausente=" + ausente + ", bautizo=" + bautizo + ", casa=" + casa + ", padre=" + padre + ", madre=" + madre + '}';
    }

    
    
    
   
    
    
    

    

    

}
