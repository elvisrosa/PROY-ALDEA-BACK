package com.aldea.cristo.persistencia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter 
@NoArgsConstructor


@Entity
@Table(name = "datos_madre")
public class MadreEntity {

 
    @Id
    private String cedula;

    private String nombre;

    private String apellidos;

    @Column(name = "fecha_nacimiento")
    private Timestamp fechaNacimiento;

    private Integer edad;

    private String telefono;
    
    @OneToMany(mappedBy = "madre", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<NinoEntity> niños;

  
    @Override
    public String toString() {
        return "MadreEntity{" + "cedula=" + cedula + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento + ", edad=" + edad + ", telefono=" + telefono + ", ni\u00f1os=" + niños + '}';
    }

    

}
