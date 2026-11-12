package com.krakedev.proyectos.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "clave_secreta_super_segura_proyectos_db";
    private final long EXPIRATION_TIME = 86400000; // 24 horas

    public String generarToken(String username, String rol) {
        return JWT.create()
                .withSubject(username)
                .withClaim("rol", rol)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String validarYObtenerUsername(String token) {
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build()
                    .verify(token);
            return jwt.getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
    
    public String obtenerRol(String token) {
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build()
                    .verify(token);
            return jwt.getClaim("rol").asString();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}