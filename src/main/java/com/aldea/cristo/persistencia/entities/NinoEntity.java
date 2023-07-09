
package com.aldea.cristo.persistencia.entities;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "nino")

public class NinoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_niño", nullable = false)
    private Integer idNino;

    @Column(name = "cedula")
    private String cedula;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "lugar_nacimiento")
    private String lugarNacimiento;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "sexo")
    private String sexo;

    @ManyToOne
    @JoinColumn(name = "id_padre")
    private PadreEntity padre;

    @ManyToOne
    @JoinColumn(name = "id_madre")
    private MadreEntity madre;

    @ManyToOne
    @JoinColumn(name = "id")
    private UserEntity users;

    @ManyToOne
    @JoinColumn(name = "id_casa")
    private CasaEntity casa;

    @ManyToOne
    @JoinColumn(name = "id_bautismo")
    private BautismoEntity bautismo;

    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private GrupoEntity grupo;
    
    @OneToMany(mappedBy = "nino", fetch = FetchType.EAGER)
    private List<FichaMedicaEntity> fichasMedicas;

    @OneToMany(mappedBy = "nino", fetch = FetchType.EAGER)
    private List<BitacoraEntity> bitacoras;

    @OneToMany(mappedBy = "nino", fetch = FetchType.EAGER)
    private List<EstudiosEntity> estudios;
      
    public NinoEntity() {
    }

    public NinoEntity(Integer idNino, String cedula, String nombres, String apellidos, LocalDate fechaNacimiento, String lugarNacimiento, Integer edad, String sexo, PadreEntity padre, MadreEntity madre, UserEntity users, CasaEntity casa, BautismoEntity bautismo, GrupoEntity grupo) {
        this.idNino = idNino;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.lugarNacimiento = lugarNacimiento;
        this.edad = edad;
        this.sexo = sexo;
        this.padre = padre;
        this.madre = madre;
        this.users = users;
        this.casa = casa;
        this.bautismo = bautismo;
        this.grupo = grupo;
    }

    public Integer getIdNino() {
        return idNino;
    }

    public void setIdNino(Integer idNino) {
        this.idNino = idNino;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public PadreEntity getPadre() {
        return padre;
    }

    public void setPadre(PadreEntity padre) {
        this.padre = padre;
    }

    public MadreEntity getMadre() {
        return madre;
    }

    public void setMadre(MadreEntity madre) {
        this.madre = madre;
    }

    public UserEntity getUsers() {
        return users;
    }

    public void setUsers(UserEntity users) {
        this.users = users;
    }

    public CasaEntity getCasa() {
        return casa;
    }

    public void setCasa(CasaEntity casa) {
        this.casa = casa;
    }

    public BautismoEntity getBautismo() {
        return bautismo;
    }

    public void setBautismo(BautismoEntity bautismo) {
        this.bautismo = bautismo;
    }

    public GrupoEntity getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoEntity grupo) {
        this.grupo = grupo;
    }

    public List<FichaMedicaEntity> getFichasMedicas() {
        return fichasMedicas;
    }

    public void setFichasMedicas(List<FichaMedicaEntity> fichasMedicas) {
        this.fichasMedicas = fichasMedicas;
    }

    public List<BitacoraEntity> getBitacoras() {
        return bitacoras;
    }

    public void setBitacoras(List<BitacoraEntity> bitacoras) {
        this.bitacoras = bitacoras;
    }

    public List<EstudiosEntity> getEstudios() {
        return estudios;
    }

    public void setEstudios(List<EstudiosEntity> estudios) {
        this.estudios = estudios;
    }
 
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNino != null ? idNino.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof NinoEntity)) {
            return false;
        }
        NinoEntity other = (NinoEntity) object;
        if ((this.idNino == null && other.idNino != null) || (this.idNino != null && !this.idNino.equals(other.idNino))) {
            return false;
        }
        return true;
    }
    
}
