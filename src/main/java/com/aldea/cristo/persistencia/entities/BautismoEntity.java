package com.aldea.cristo.persistencia.entities;
import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor  
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
    
    @Override
    public String toString() {
        return "{" + "fecha=" + fecha + ", descripcionPadrino=" + descripcionPadrino + ", matrimoniosPadres=" + matrimoniosPadres  + '}';
    }


}
