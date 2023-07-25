package com.aldea.cristo.persistencia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "tutora")
public class TutoraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tutora", nullable = false)
    private Integer idTutora;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    @JoinTable(
    name = "tutor_casa",
            joinColumns = @JoinColumn(name = "tutor_id", referencedColumnName = "id_tutora"),
            inverseJoinColumns = @JoinColumn(name = "casa_id", referencedColumnName = "id_casa")
    )*/
    @ManyToMany(mappedBy = "tutores")
    private List<CasaEntity> casas;
    
    

}
