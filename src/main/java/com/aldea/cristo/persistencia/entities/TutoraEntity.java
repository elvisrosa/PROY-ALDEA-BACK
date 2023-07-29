package com.aldea.cristo.persistencia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
@Entity
@Table(name = "tutora")
public class TutoraEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tutora", nullable = false)
    private Integer idTutora;

    private String nombre;

    private String apellido;
    
    private String correo;
    
    private String telefono;
    
    private String cedula;
    

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
    name = "tutor_casa",
            joinColumns = @JoinColumn(name = "tutor_id", referencedColumnName = "id_tutora"),
            inverseJoinColumns = @JoinColumn(name = "casa_id", referencedColumnName = "id_casa")
    )
    private Set<CasaEntity> casas = new HashSet<>();
}

