package com.aldea.cristo.persistencia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bitacora")
public class BitacoraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bitacora", nullable = false)
    private Integer idBitacora;

    @Column(name = "fecha_creacion")
    private Timestamp fechaRegistro;

    @Column(name = "ultima_modificacion")
    private Timestamp ultimaModificacion;

    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "url_bitacora")
    private String url;
    
    //@Lob
    @Column(name = "archivo_bitacora")
    private byte[] archivo;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "casa_id")
    private CasaEntity casa;

    @PrePersist
    public void prePersist() {
        this.ultimaModificacion = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        this.ultimaModificacion = Timestamp.valueOf(LocalDateTime.now());
    }

}
