/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aldea.cristo.web.controllers;

import com.aldea.cristo.servicios.dtos.LoginDto;
import com.aldea.cristo.web.JWTUtil.jwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author elvis
 */
@CrossOrigin(origins = {"http://localhost:4200"})

@RestController
@RequestMapping("/api/auth")
public class authController {

    private final static Logger logger = LoggerFactory.getLogger(authController.class);
    
    private final AuthenticationManager authenticationManager;
    private final jwtUtil jwtUtil;

    @Autowired
    public authController(AuthenticationManager authenticationManager, jwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(login);

        //System.out.println(authentication.isAuthenticated());
        //System.out.println(authentication.getPrincipal());
        //System.out.println("authentication" + authentication);
        String jwt = this.jwtUtil.create(loginDto.getUsername());

        //return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
        //return new ResponseEntity<>(jwt, HttpStatus.OK);
        logger.info("Respuesta del JWT" + jwt);
        return ResponseEntity.ok(jwt);

    }
}
