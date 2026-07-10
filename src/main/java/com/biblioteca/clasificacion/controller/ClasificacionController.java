package com.biblioteca.clasificacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.biblioteca.clasificacion.dto.ClasificacionRequestDTO;
import com.biblioteca.clasificacion.dto.ClasificacionResponseDTO;
import com.biblioteca.clasificacion.service.ClasificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/api/v2/clasificaciones")
@Tag(name = "Clasificaciones", description = "Operaciones de clasificaciones PEGI y ESRB")
public class ClasificacionController {
    @Autowired
    private ClasificacionService clasificacionService;
    @GetMapping
    @Operation(summary = "Listar todas las clasificaciones")
    public List<ClasificacionResponseDTO> obtenerTodas() {
        return clasificacionService.obtenerTodas();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una clasificacion por ID")
    public ResponseEntity<ClasificacionResponseDTO> obtenerPorId(@PathVariable Long id) {
        return clasificacionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/edad-minima/{edadMinima}")
    @Operation(summary = "Listar clasificaciones por edad minima")
    public List<ClasificacionResponseDTO> obtenerPorEdadMinima(@PathVariable Integer edadMinima) {
        return clasificacionService.obtenerPorEdadMinima(edadMinima);
    }
    @GetMapping("/permitidas")
    @Operation(summary = "Listar clasificaciones permitidas para una edad")
    public List<ClasificacionResponseDTO> obtenerPermitidasParaEdad(@RequestParam Integer edad) {
        return clasificacionService.obtenerPermitidasParaEdad(edad);
    }
    @PostMapping
    @Operation(summary = "Crear una clasificacion")
    public ResponseEntity<ClasificacionResponseDTO> crear(@Valid @RequestBody ClasificacionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clasificacionService.crear(dto));
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una clasificacion")
    public ResponseEntity<ClasificacionResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClasificacionRequestDTO dto) {
        return clasificacionService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una clasificacion")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clasificacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
