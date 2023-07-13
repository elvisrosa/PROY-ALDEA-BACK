package com.aldea.cristo.web.controllers;
import com.aldea.cristo.persistencia.entities.UserEntity;
import com.aldea.cristo.persistencia.entities.UserRolEntity;
import com.aldea.cristo.servicios.userService;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserControlador {

    private static final Logger logger = LoggerFactory.getLogger(UserControlador.class);

    @Autowired
    private userService userService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> save(@RequestBody UserEntity user) {
        UserEntity usere = new UserEntity();
        List<UserRolEntity> roles = mapToUserRoleEntities(user);
        try {
            usere.setUsername(user.getUsername());
            usere.setCorreo(user.getCorreo());
            usere.setDisabled(user.getDisabled());
            usere.setLocked(user.getLocked());
            usere.setNombre(user.getNombre());
            usere.setPassword(userService.encryptar(user.getPassword()));
            userService.save(usere, roles);

        } catch (Exception e) {
            e.getStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok("Usuario creado correctamente");
    }

    @PutMapping("/actualizar/{username}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> actualizar(@RequestBody UserEntity user, @PathVariable String username) {
        UserEntity userE = userService.findById(username);
        logger.info("UserE"+ userE);
        if (null == userE) {
            return ResponseEntity.notFound().build();
        }
        try {
            userE.setDisabled(user.getDisabled());
            userE.setLocked(user.getLocked());
            userE.setNombre(user.getNombre());
            userE.setRoles(user.getRoles());
            userE.setCorreo(user.getCorreo());
            //userE.setPassword(user.getPassword());
            List<UserRolEntity> roles = mapToUserRoleEntities(user);
            userService.save(userE, roles);
            return ResponseEntity.ok("Usuario actualizado correctamente");
        } catch (Exception e) {
            e.getStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private List<UserRolEntity> mapToUserRoleEntities(UserEntity userRequest) {
        logger.info(userRequest.getUsername());
        return userRequest.getRoles().stream()
                .map(roleRequest -> {
                    UserRolEntity roleEntity = new UserRolEntity();
                    roleEntity.setUsername(userRequest.getUsername());
                    roleEntity.setRole(roleRequest.getRole());
                    logger.info(roleEntity.toString());
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
    public void eliminar(@PathVariable String username) {
        userService.deleteById(username);
    }
}
