package com.aldea.cristo.persistencia.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class UserEntity {

    @Id
    @Column(nullable = false, length = 20)
    private String username;

    /*@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;*/
    @Column(name = "nombre")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "bloqueado")
    private Boolean locked;

    @Column(name = "deshabilitado")
    private Boolean disabled;

    @Column(name = "contrasena", length = 200)
    private String password;

    //private String correo;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, 
        cascade = {CascadeType.MERGE, 
            CascadeType.REMOVE, 
                CascadeType.PERSIST,
                    })
    private List<UserRolEntity> roles;

    @OneToOne(cascade = {
        CascadeType.REMOVE,
                CascadeType.MERGE,
                    CascadeType.PERSIST
    }, orphanRemoval = true)
    private TutoraEntity tutor;

    @Override
    public String toString() {
        return "UserEntity{" + "username=" + username + ", locked=" + locked + ", disabled=" + disabled + ", password=" + password + ", correo=" + ", roles=" + roles + '}';
    }

}
