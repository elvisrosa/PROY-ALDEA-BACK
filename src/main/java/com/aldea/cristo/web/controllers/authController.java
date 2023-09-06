package com.aldea.cristo.web.controllers;

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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        Authentication authentication = null;
        try {
            logger.info("loginDto.toString()".concat(loginDto.toString()));
            UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            authentication = this.authenticationManager.authenticate(login);
            //OBTENEMOS LOS DATOS DEL USUARIO AUTENTICADO
            ResponseUsuario user = userService.MostrarDatosUsuario(loginDto.getUsername());
            String jwt = this.jwtUtil.create(loginDto.getUsername());
            mensaje.put("usuario", user);
            mensaje.put("estado", true);
            mensaje.put("token", jwt);
            return ResponseEntity.ok(mensaje);
        } catch (UsernameNotFoundException e) {
            mensaje.put("message", "El nombre de usuario no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        } catch (BadCredentialsException e) {
            mensaje.put("message", "Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mensaje);
        } catch (AuthenticationException e) {
            mensaje.put("message", "Error al iniciar sesión, comunícate con desarrollo");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje);
        }

    }

    
}
