package com.aldea.cristo.persistencia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;

@Data
@Entity
@Table(name = "casa")
public class CasaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_casa", nullable = false)
    private Integer idCasa;

    @Column(name = "numero_casa")
    private String numeroCasa;

    @Column(length = 10)
    private String telefono;

    private String direccion;

    @OneToMany(mappedBy = "casa", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<NinoEntity> niños;

    private String img;
    
    @Column(name = "estado", nullable = true)
    private Integer estado;

    @OneToOne(mappedBy = "casa", cascade = CascadeType.ALL, orphanRemoval = true) // orphanRemoval = true
    private BitacoraEntity bitacora;

    //@JsonIgnore
    @ManyToMany(mappedBy = "casas", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<TutoraEntity> tutores = new HashSet<>();
    
    @Override
    public String toString() {
        return "CasaEntity{" + "idCasa=" + idCasa + ", numeroCasa=" + numeroCasa + ", telefono=" + telefono + ", direccion=" + direccion + ", ni\u00f1os=" + niños + '}';
    }
 

}
