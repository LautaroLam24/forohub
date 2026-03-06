package com.aluralatam.forohub.controller;

import com.aluralatam.forohub.domain.topico.DatosActualizadosTopico;
import com.aluralatam.forohub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

            if (repository.existsByTituloAndMensaje(datos.titulo(),datos.mensaje())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe ese topico!");
            }

            var topico = new Topico(datos);
            repository.save(topico);

            var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
            return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
        }

        @GetMapping
        public ResponseEntity <Page<DatosListaTopicos>> listar(@PageableDefault (size = 10, sort = {"fechaCreacion", "titulo"},direction = Sort.Direction.ASC)Pageable paginacion){
            var page = repository.findAll(paginacion).map(DatosListaTopicos::new);

            return ResponseEntity.ok(page);
        }

        @GetMapping("/{id}")
        public ResponseEntity detalle(@PathVariable Long id){

            var topico = repository.findById(id);

            if (topico.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Topico con ese id no existe");
            }

            var datos = new DatosDetalleTopico(topico.get());
            return ResponseEntity.ok(datos);

        }

        @Transactional
        @PutMapping ("/{id}")
        public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizadosTopico datos){

            var topico = repository.findById(id);

            if (topico.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El topico con el id ingresado no existe");
            }

            var topicoEncontrado = topico.get();
            topicoEncontrado.actualizarInformacionTopico(datos);
            return ResponseEntity.ok(new DatosDetalleTopico(topicoEncontrado));
        }

        @Transactional
        @DeleteMapping("/{id}")
        public ResponseEntity eliminar (@PathVariable Long id){
            var topico = repository.findById(id);

            if (topico.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El topico con el id ingresado no existe");
            }

            var topicoEncontrado = topico.get();
            repository.deleteById(topicoEncontrado.getId());
            return ResponseEntity.ok().body("Topico eliminado con exito!");
        }
}
