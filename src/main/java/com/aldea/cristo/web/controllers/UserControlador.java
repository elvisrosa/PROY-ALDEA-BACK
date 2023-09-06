package com.aldea.cristo.web.controllers;

import com.aldea.cristo.persistencia.entities.TutoraEntity;
import com.aldea.cristo.persistencia.entities.UserEntity;
import com.aldea.cristo.persistencia.entities.UserRolEntity;
import com.aldea.cristo.servicios.TutorService;
import com.aldea.cristo.servicios.UserRoleService;
import com.aldea.cristo.servicios.userService;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserControlador {

    //private static final Logger logger = LoggerFactory.getLogger(UserControlador.class);
    @Autowired
    private userService userService;

    @Autowired
    //@Qualifier("servicioTutor")
    private TutorService servicioTutor;

    @Autowired
    private UserRoleService servicioRol;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> save(@RequestBody UserEntity user) {
        List<UserRolEntity> roles = mapToUserRoleEntities(user);
        boolean existeRolTutor = roles.stream().anyMatch(resp -> "TUTOR".equals(resp.getRole()));
        try {
            TutoraEntity tutorCreado = null;
            if (existeRolTutor) {
                tutorCreado = servicioTutor.save(user.getTutor());
            }
            UserEntity usere = new UserEntity();
            usere.setTutor(tutorCreado);
            usere.setUsername(user.getUsername());
            usere.setDisabled(user.getDisabled());
            usere.setLocked(user.getLocked());
            usere.setNombres(user.getNombres());
            usere.setApellidos(user.getApellidos());
            if (user.getPassword() != null) {
                usere.setPassword(userService.encryptar(user.getPassword()));
            }
            userService.save(usere, roles);
            return ResponseEntity.status(HttpStatus.CREATED).body(usere);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"El usuario ya existe, intenta con otro usuario\"}");
        } catch (Exception e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error al crear usuario\"}");
        }
    }

    @PatchMapping("/actualizar/{username}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> actualizar(@PathVariable String username, @RequestBody UserEntity user) {
        UserEntity userE = userService.findById(username);
        if (userE == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Usuario no existe\"}");
        }
        try {
            if (user.getPassword() != null) {
                userE.setPassword(userService.encryptar(user.getPassword()));
            }
            userE.setDisabled(user.getDisabled());
            userE.setLocked(user.getLocked());
            userE.setNombres(user.getNombres());
            userE.setApellidos(user.getApellidos());
            List<UserRolEntity> roles = mapToUserRoleEntities(user);

            Iterator<UserRolEntity> iterator = userE.getRoles().iterator();
            System.out.println("Iterator" + iterator);
            while (iterator.hasNext()) {
                UserRolEntity roleExist = iterator.next();
                if (!roles.contains(roleExist)) {
                    iterator.remove();
                    roleExist.setUser(null);
                    servicioRol.delete(username);
                }
            }

            userService.save(userE, roles);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Usuario no existe\"}");
        } catch (Exception e) {
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error al actualizar usuario\"}");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"Error al actualizar usuario: " + e.getMessage() + "\"}");

        }
        return ResponseEntity.ok().body("{\"message\": \"Usuario actualizado correctamente\"}");
    }

    private List<UserRolEntity> mapToUserRoleEntities(UserEntity userRequest) {
        return userRequest.getRoles().stream()
                .map(roleRequest -> {
                    UserRolEntity roleEntity = new UserRolEntity();
                    roleEntity.setUsername(userRequest.getUsername());
                    roleEntity.setRole(roleRequest.getRole());
                    return roleEntity;
                }).collect(Collectors.toList());
    }

    @GetMapping("/todo")
    public List<UserEntity> listar() {
        return userService.findAll();
    }

    @GetMapping("/{username}")
    public UserEntity listarUsername(@PathVariable String username) {
        return userService.findById(username);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> eliminar(@PathVariable String username) {
        try {
            userService.deleteById(username);
            return ResponseEntity.ok().body("{\"message\": \"Usuario eliminado correctamente\"}");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Usuario no existe\"}");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error al eliminar usuario\"}");
        }
    }

}
