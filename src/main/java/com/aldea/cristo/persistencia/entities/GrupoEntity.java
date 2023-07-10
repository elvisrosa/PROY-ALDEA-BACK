package com.aldea.cristo.persistencia.entities;

import jakarta.persistence.*;
import java.util.List;



//@Entity
//@Table(name = "grupo")
public class GrupoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo", nullable = false)
    private Integer idGrupo;

    @ManyToOne
    @JoinColumn(name = "id_tutora")
    private TutoraEntity tutora;
    
    @OneToMany(mappedBy = "grupo", fetch = FetchType.EAGER)
    private List<NinoEntity> niños;

    public GrupoEntity() {
    }

    public GrupoEntity(Integer idGrupo, TutoraEntity tutora) {
        this.idGrupo = idGrupo;
        this.tutora = tutora;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public TutoraEntity getTutora() {
        return tutora;
    }

    public void setTutora(TutoraEntity tutora) {
        this.tutora = tutora;
    }

    public List<NinoEntity> getNiños() {
        return niños;
    }

    public void setNiños(List<NinoEntity> niños) {
        this.niños = niños;
    }
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupo != null ? idGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof GrupoEntity)) {
            return false;
        }
        GrupoEntity other = (GrupoEntity) object;
        if ((this.idGrupo == null && other.idGrupo != null) || (this.idGrupo != null && !this.idGrupo.equals(other.idGrupo))) {
            return false;
        }
        return true;
    }

    
}