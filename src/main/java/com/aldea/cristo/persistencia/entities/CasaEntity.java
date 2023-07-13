package com.aldea.cristo.persistencia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "casa")
public class CasaEntity {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_casa", nullable = false)
    private Integer idCasa;

    @Column(name = "numero_casa")
    private String numeroCasa;

    @Column(length = 10)
    private String telefono;

    private String direccion;
    
    @OneToMany(mappedBy = "casa", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<NinoEntity> niños;
}