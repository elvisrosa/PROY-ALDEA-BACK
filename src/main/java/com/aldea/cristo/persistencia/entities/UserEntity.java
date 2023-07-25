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

    private String nombre;

    @Column(name = "bloqueado")
    private Boolean locked;

    @Column(name = "deshabilitado")
    private Boolean disabled;
     
    @Column(name = "contrasena", nullable = false, length = 200)
    private String password;
    
    private String correo;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UserRolEntity> roles;
      

    @Override
    public String toString() {

        return "UserEntity{" + "username=" + username + ", nombre=" + nombre + ", locked=" + locked + ", disabled=" + disabled + ", password=" + password + ", correo=" + correo + ", roles=" + roles + '}';
    }

    
 
    
    
    
    
    

}