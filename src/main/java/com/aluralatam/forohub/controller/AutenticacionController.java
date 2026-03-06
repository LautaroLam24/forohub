package com.aluralatam.forohub.controller;

import com.aluralatam.forohub.domain.usuario.DatosAutenticacion;
import com.aluralatam.forohub.domain.usuario.Usuario;
import com.aluralatam.forohub.infra.security.DatosToken;
import com.aluralatam.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datosAutenticacion) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                datosAutenticacion.username(),
                datosAutenticacion.password()
        );

        var autenticacion = authenticationManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

        return ResponseEntity.ok(new DatosToken(tokenJWT));
    }
}