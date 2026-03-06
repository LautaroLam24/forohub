package com.aluralatam.forohub.domain.topico;

import java.time.LocalDateTime;

public record DatosActualizadosTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        String autor,
        String curso
) {


}
