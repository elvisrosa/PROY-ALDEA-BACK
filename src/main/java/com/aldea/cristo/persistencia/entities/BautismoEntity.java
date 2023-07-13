package com.aldea.cristo.persistencia.entities;
import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.*;



@Getter
@Setter
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
    

    public BautismoEntity() {
    }

    public BautismoEntity(Integer idBautismo, Timestamp fecha, String descripcionPadrino, String matrimoniosPadres) {
        this.idBautismo = idBautismo;
        this.fecha = fecha;
        this.descripcionPadrino = descripcionPadrino;
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

    @Override
    public String toString() {
        return "{" + "fecha=" + fecha + ", descripcionPadrino=" + descripcionPadrino + ", matrimoniosPadres=" + matrimoniosPadres  + '}';
    }


}
