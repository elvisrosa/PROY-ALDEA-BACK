package com.aldea.cristo.web.controllers;

import com.aldea.cristo.persistencia.entities.UserEntity;
import com.aldea.cristo.servicios.dtos.LoginDto;
import com.aldea.cristo.servicios.dtos.ResponseUsuario;
import com.aldea.cristo.servicios.userService;
import com.aldea.cristo.web.JWTUtil.jwtUtil;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author elvis
 */
//@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class authController {

    private final static Logger logger = LoggerFactory.getLogger(authController.class);
    private final AuthenticationManager authenticationManager;
    private final jwtUtil jwtUtil;
    private final userService userService;

    @Autowired
    public authController(AuthenticationManager authenticationManager, jwtUtil jwtUtil, userService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) {
        Map<String, Object> mensaje = new HashMap<>();
        ResponseUsuario responseDTO;
        try {
            UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            Authentication authentication = this.authenticationManager.authenticate(login);

            //OBTENEMOS LOS DATOS DEL USUARIO AUTENTICADO
            ResponseUsuario user = userService.MostrarDatosUsuario(loginDto.getUsername());

            responseDTO = new ResponseUsuario();
            responseDTO.setUsername(user.getUsername());
            responseDTO.setNombre(user.getNombre());
            responseDTO.setCorreo(user.getCorreo());
            responseDTO.setRoles(user.getRoles());

        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String jwt = this.jwtUtil.create(loginDto.getUsername());
        mensaje.put("Usuario", responseDTO);
        mensaje.put("Estado", true);
        mensaje.put("token", jwt);
        //mensaje.put("Usuario", userService.MostrarDatosUsuario(jwtUtil.getUsername(jwt)));
        logger.info("Usuario" + jwtUtil.getUsername(jwt));
        return ResponseEntity.ok(mensaje);

    }

    @GetMapping("/saludo/hola")
    public ResponseEntity<?> getHOlaElvis() {
        return ResponseEntity.ok("HOla elvic como estas");
    }
}
