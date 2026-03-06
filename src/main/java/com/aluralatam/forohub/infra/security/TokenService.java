package com.aluralatam.forohub.infra.security;

import com.aluralatam.forohub.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secreto;

    public String generarToken(Usuario usuario){

        try {
            var algortimo = Algorithm.HMAC256(secreto);
            return JWT.create()
                    .withIssuer("API ForoHub")
                    .withSubject(usuario.getPassword())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algortimo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error al generar el token JWT", exception);
        }
    }

    private Instant fechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT){
        try {
            var algortimo = Algorithm.HMAC256(secreto);
            return JWT.require(algortimo)
                    .withIssuer("API ForoHub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTCreationException e){
            throw new RuntimeException("Token JWT invalido o expirado!");
        }
    }

}
