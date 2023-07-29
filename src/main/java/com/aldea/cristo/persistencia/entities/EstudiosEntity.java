package com.aldea.cristo.persistencia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "estudios")
public class EstudiosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudios", nullable = false)
    private Integer idEstudio;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_niño")
    private NinoEntity nino;

    @Column(name = "niveles")
    private String niveles;

    @Column(name = "nombre_institucion")
    private String nombreInstitucion;
    
    @Column(name = "sotenimiento")
    private String sotenimiento;
    
    @Column(name = "provincia")
    private String provincia;

    @Column(name = "ciudad")
    private String ciudad;
    
    @Column(name = "jornada")
    private String jornada;

    @Column(name = "regimen_escolar")
    private String regimenEscolar;

    @Column(name = "modalidad")
    private String modalidad;

    @Column(name = "promedio")
    private float promedio;
        
   
}
