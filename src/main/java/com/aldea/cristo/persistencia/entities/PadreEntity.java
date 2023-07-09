
package com.aldea.cristo.persistencia.entities;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
/**
 *
 * @author Bajaña
 */
@Entity
@Table(name = "datos_padre")

public class PadreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_padre", nullable = false)
    private Integer idPadre;

    @Column(name = "cedula")
    private String cedula;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "telefono")
    private String telefono;
    
    @OneToMany(mappedBy = "datos_padre", fetch = FetchType.EAGER)
    private List<NinoEntity> niños;

    public PadreEntity() {
    }

    public PadreEntity(Integer idPadre, String cedula, String nombre, String apellidos, LocalDate fechaNacimiento, Integer edad, String telefono) {
        this.idPadre = idPadre;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.telefono = telefono;
    }

    
    public Integer getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Integer idPadre) {
        this.idPadre = idPadre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        hash += (idPadre != null ? idPadre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        
        if (!(object instanceof PadreEntity)) {
            return false;
        }
        PadreEntity other = (PadreEntity) object;
        if ((this.idPadre == null && other.idPadre != null) || (this.idPadre != null && !this.idPadre.equals(other.idPadre))) {
            return false;
        }
        return true;
    }
}
