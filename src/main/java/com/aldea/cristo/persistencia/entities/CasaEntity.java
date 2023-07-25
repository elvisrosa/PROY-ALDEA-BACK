package com.aldea.cristo.persistencia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    private String img;

    @OneToOne(mappedBy = "casa", cascade = CascadeType.ALL, orphanRemoval = true) // orphanRemoval = true
    private BitacoraEntity bitacora;

    /*@ManyToMany(mappedBy = "casas", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TutoraEntity> tutores;*/
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Tutor_Casa",
               joinColumns = @JoinColumn(name = "casa_id"),
               inverseJoinColumns = @JoinColumn(name = "tutor_id"))
    private List<TutoraEntity> tutores;

    @Override
    public String toString() {
        return "CasaEntity{" + "idCasa=" + idCasa + ", numeroCasa=" + numeroCasa + ", telefono=" + telefono + ", direccion=" + direccion + ", ni\u00f1os=" + niños + '}';
    }

}
