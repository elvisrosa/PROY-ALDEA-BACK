package com.aldea.cristo.persistencia.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "bautismo")
public class BautismoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bautismo", nullable = false)
    private Integer idBautismo;

    @Column(name = "fecha")
    private Timestamp fecha;

    @Column(name = "descripcion_padrino")
    private String descripcionPadrino;

    @Column(name = "matrimonios_padres")
    private String matrimoniosPadres;
    
    //@OneToMany(mappedBy = "bautismo", fetch = FetchType.EAGER)
    //private List<NinoEntity> niños;

    //RELACION ONETONE CON NINOENTITY 1 NIÑO Y 1 BAUTIZO
    @OneToOne
    @JoinColumn(name = "cedula_nino", referencedColumnName = "cedula", insertable = false, updatable = false)
    private NinoEntity nino;
    
    public BautismoEntity() {
    }

    public BautismoEntity(Integer idBautismo, Timestamp fecha, String descripcionPadrino, String matrimoniosPadres) {
        this.idBautismo = idBautismo;
        this.fecha = fecha;
        this.descripcionPadrino = descripcionPadrino;
        this.matrimoniosPadres = matrimoniosPadres;
    }

    public Integer getIdBautismo() {
        return idBautismo;
    }

    public void setIdBautismo(Integer idBautismo) {
        this.idBautismo = idBautismo;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getDescripcionPadrino() {
        return descripcionPadrino;
    }

    public void setDescripcionPadrino(String descripcionPadrino) {
        this.descripcionPadrino = descripcionPadrino;
    }

    public String getMatrimoniosPadres() {
        return matrimoniosPadres;
    }

    public void setMatrimoniosPadres(String matrimoniosPadres) {
        this.matrimoniosPadres = matrimoniosPadres;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBautismo != null ? idBautismo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
       
        
        if (!(object instanceof BautismoEntity)) {
            return false;
        }
        BautismoEntity other = (BautismoEntity) object;
        if ((this.idBautismo == null && other.idBautismo != null) || (this.idBautismo != null && !this.idBautismo.equals(other.idBautismo))) {
            return false;
        }
        return true;
    }
  
    
}