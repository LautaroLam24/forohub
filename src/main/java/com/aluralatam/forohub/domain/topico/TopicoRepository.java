package com.aluralatam.forohub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico,Long> {

    boolean existsByTituloAndMensaje (String titulo, String mensaje);

    Optional<Topico> findById(Long id);
}
