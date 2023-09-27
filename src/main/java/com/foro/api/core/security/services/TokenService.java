package com.foro.api.core.security.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.foro.api.model.UsuarioModel;

@Service
public class TokenService {

    @Value("${app.jwt.issuer}")
    private String issuer;
    @Value("${app.jwt.secret}")
    private String secret;

    private Instant generarFechaDeExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    public String generar(UsuarioModel usuarioModel) {
        Algorithm algoritmo = Algorithm.HMAC256(secret);
        return JWT
                .create()
                .withIssuer(issuer)
                .withSubject(usuarioModel.getNombreUsuario())
                .withClaim("id", usuarioModel.getId())
                .withExpiresAt(generarFechaDeExpiracion())
                .sign(algoritmo);
    }

    public String getSubject(String token) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            DecodedJWT verifier = JWT
                    .require(algoritmo)
                    .withIssuer(issuer)
                    .build()
                    .verify(token);
            return verifier.getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

}
