package com.aldea.cristo.web.JWTUtil;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

@Component
public class jwtUtil {

    private static final String SECRET_KEY = "4ld3a-cr1st0";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    //Al crear el token deberias mandarle los datos del usuario- todos, es decir una entidad de tipo USerEntity
    public String create(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer("aldea-cristo")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)))
                .sign(ALGORITHM);
    }

    public boolean isValid(String token) {
        try {
            JWT.require(ALGORITHM)
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }

    }

    public String getUsername(String token) {     
        return JWT.require(ALGORITHM)
                .build()
                .verify(token)
                .getSubject();
    }

}