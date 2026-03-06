package com.aluralatam.forohub.controller;

import com.aluralatam.forohub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping ("/topicos")

//indicamos bearer  despues

public class TopicoController {

        @Autowired
        private TopicoRepository repository;

        @PostMapping
        public ResponseEntity registrar (@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder){
            var topico = new Topico(datos);
            repository.save(topico);

            var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
            return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
        }

        @GetMapping
        public ResponseEntity <Page<DatosListaTopicos>> listar(@PageableDefault (size = 10, sort = {"fechaCreacion"})Pageable paginacion){
            var page = repository.findAll(paginacion).map(DatosListaTopicos::new);

            return ResponseEntity.ok(page);
        }
}
