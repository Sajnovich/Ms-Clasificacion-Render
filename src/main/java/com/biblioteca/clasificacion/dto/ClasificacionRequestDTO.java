package com.biblioteca.clasificacion.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClasificacionRequestDTO {

    @NotBlank(message = "PEGI es obligatorio")
    @Size(max = 20, message = "PEGI no puede superar 20 caracteres")
    private String pegi;

    @NotBlank(message = "ESRB es obligatorio")
    @Size(max = 20, message = "ESRB no puede superar 20 caracteres")
    private String esrb;

    @NotNull(message = "La edad minima es obligatoria")
    @Min(value = 0, message = "La edad minima no puede ser negativa")
    @Max(value = 18, message = "La edad minima maxima esperada es 18")
    private Integer edadMinima;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 120, message = "El nombre no puede superar 120 caracteres")
    private String nombre;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(max = 500, message = "La descripcion no puede superar 500 caracteres")
    private String descripcion;
}
