/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aldea.cristo.servicios;

import com.aldea.cristo.persistencia.entities.UserEntity;
import com.aldea.cristo.persistencia.entities.UserRolEntity;
import com.aldea.cristo.persistencia.repository.userRepository;
import com.aldea.cristo.servicios.dtos.ResponseUsuario;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author elvis
 */
@Service
public class userService implements UserDetailsService {

    UserEntity usuario = new UserEntity();
    private static final Logger logger = LoggerFactory.getLogger(userService.class);
    private final userRepository userRepository;

    @Autowired
    public userService(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*public ResponseUsuario MostrarDatosUsuario(String username) {
        UserEntity userEntity = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));

        // Construir el objeto UserResponse con los datos necesarios
        ResponseUsuario userResponse = new ResponseUsuario();
        userResponse.setUsername(userEntity.getUsername());
        userResponse.setNombre(userEntity.getNombre());
        userResponse.setCorreo(userEntity.getCorreo());

        // Construir el objeto UserDetails para la autenticación
        String[] roles = userEntity.getRoles().stream()
                .map(UserRolEntity::getRole)
                .toArray(String[]::new);
        
        userResponse.setRoles(roles);

        UserDetails userDetails = User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(roles)
                .accountLocked(userEntity.getLocked())
                .disabled(userEntity.getDisabled())
                .build();
        

        return userResponse;
    }*/
    public ResponseUsuario MostrarDatosUsuario(String username) {
        UserEntity userEntity = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));

        ResponseUsuario userDto = new ResponseUsuario();
        userDto.setUsername(userEntity.getUsername());
        userDto.setNombre(userEntity.getNombre());
        userDto.setCorreo(userEntity.getCorreo());
        List<String> roles = new ArrayList<>();
        userEntity.getRoles().forEach(resp -> {
            roles.add(resp.getRole());
        });
        userDto.setRoles(roles);
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("User"
                + username + "Not found"));
        usuario.setCorreo(userEntity.getCorreo());
        usuario.setNombre(userEntity.getNombre());
        usuario.setUsername(userEntity.getUsername());

        logger.info("Usuario de la base de datos" + userEntity);

        String[] roles = userEntity.getRoles().stream().map(UserRolEntity::getRole).toArray(String[]::new);

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(roles)
                //.authorities(this.grantedAuthorities(roles))
                .accountLocked(userEntity.getLocked())
                .disabled(userEntity.getDisabled())
                .build();
    }

    /*private String[] getAuthorities(String role){
        if("ADMIN".equals(role) || "CUSTOMER".equals(role)){
            return new String[] {"randon_order"};
        }
        return new String[]{""};
    }
    private List<GrantedAuthority> grantedAuthorities(String[] roles){
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);
        for(String role:roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
            for(String authority:this.getAuthorities(role)){
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }
        return authorities;
    }
     */
}
