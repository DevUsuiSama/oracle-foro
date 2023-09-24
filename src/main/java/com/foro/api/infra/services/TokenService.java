package com.foro.api.infra.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.foro.api.models.UsuarioModel;

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
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT
                    .create()
                    .withIssuer(issuer)
                    .withSubject(usuarioModel.getNombreUsuario())
                    .withClaim("id", usuarioModel.getId())
                    .withExpiresAt(generarFechaDeExpiracion())
                    .sign(algoritmo);
        } catch (JWTCreationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public DecodedJWT verificar(String token) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            JWTVerifier jwtVerificar = JWT
                    .require(algoritmo)
                    .withIssuer(issuer)
                    .build();
            return jwtVerificar.verify(token);
        } catch (JWTVerificationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getSubject(String token) {
        DecodedJWT verifier = null;
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            verifier = JWT
                    .require(algoritmo)
                    .withIssuer(issuer)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException e) {
            throw new RuntimeException(e.getMessage());
        }
        if (verifier == null)
            throw new RuntimeException();
        return verifier.getSubject();
    }

}
