
package com.aldea.cristo.persistencia.entities;

import java.util.List;
import javax.persistence.*;


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
    
    @OneToMany(mappedBy = "tutora", fetch = FetchType.EAGER)
    private List<GrupoEntity> grupos;
    
    public TutoraEntity() {
    }

    public TutoraEntity(Integer idTutora, String nombre, String apellido) {
        this.idTutora = idTutora;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getIdTutora() {
        return idTutora;
    }

    public void setIdTutora(Integer idTutora) {
        this.idTutora = idTutora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<GrupoEntity> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<GrupoEntity> grupos) {
        this.grupos = grupos;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTutora != null ? idTutora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof TutoraEntity)) {
            return false;
        }
        TutoraEntity other = (TutoraEntity) object;
        if ((this.idTutora == null && other.idTutora != null) || (this.idTutora != null && !this.idTutora.equals(other.idTutora))) {
            return false;
        }
        return true;
    }

}
