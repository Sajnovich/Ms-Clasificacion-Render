package com.biblioteca.clasificacion.dto;

public record ClasificacionResponseDTO(
        Long id,
        String pegi,
        String esrb,
        Integer edadMinima,
        String nombre,
        String descripcion
) {
}
