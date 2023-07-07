/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aldea.cristo.web.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class jwtFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(jwtUtil.class);
    
    private final jwtUtil jwtUtil;
    private final UserDetailsService userDetailService;

    @Autowired
    public jwtFilter(jwtUtil jwtUtil, UserDetailsService userDetailService) {
        this.jwtUtil = jwtUtil;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //BTENERMOS EL TOKEN DESDE LA CABEZERA DE LA PETICIÓN
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || header.isEmpty() || !header.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        //SEPARAMOS EL TOKEN DEL BEARER BEARE TOKEN
        String jwt = header.split(" ")[1].trim();

        //VALIDAMOS SI EL TOKEN ES VALIDO       
        if (!jwtUtil.isValid(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        //CARGAR USUARIO DESDE USERDETAILSERVICE
        String username = jwtUtil.getUsername(jwt);
        User user = (User) this.userDetailService.loadUserByUsername(username);
        logger.info("user" + user.toString());

        //CARGAR AL USUARIO EN EL CONTEXTO DE SEGURIDAD
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword(), user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }

}
