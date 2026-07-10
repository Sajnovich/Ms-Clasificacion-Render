package com.biblioteca.clasificacion.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import com.biblioteca.clasificacion.dto.ClasificacionResponseDTO;
import com.biblioteca.clasificacion.service.ClasificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@RequestMapping("/api/v2/hateoas/clasificaciones")
@Tag(name = "Clasificaciones HATEOAS", description = "Consulta de clasificaciones con enlaces de navegacion")
public class ClasificacionHateoasController {
    @Autowired
    private ClasificacionService clasificacionService;
    @Autowired
    private ClasificacionModelAssembler assembler;
    @GetMapping
    @Operation(summary = "Listar clasificaciones con enlaces HATEOAS")
    public CollectionModel<EntityModel<ClasificacionResponseDTO>> obtenerTodas() {
        return crearColeccion(clasificacionService.obtenerTodas());
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una clasificacion con enlaces HATEOAS")
    public ResponseEntity<EntityModel<ClasificacionResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return clasificacionService.obtenerPorId(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/permitidas")
    @Operation(summary = "Listar clasificaciones permitidas para una edad con enlaces HATEOAS")
    public CollectionModel<EntityModel<ClasificacionResponseDTO>> obtenerPermitidasParaEdad(
            @RequestParam Integer edad) {
        List<EntityModel<ClasificacionResponseDTO>> clasificaciones =
                clasificacionService.obtenerPermitidasParaEdad(edad).stream()
                        .map(assembler::toModel)
                        .toList();
        return CollectionModel.of(
                clasificaciones,
                linkTo(methodOn(ClasificacionHateoasController.class)
                        .obtenerPermitidasParaEdad(edad)).withSelfRel(),
                linkTo(methodOn(ClasificacionHateoasController.class)
                        .obtenerTodas()).withRel("clasificaciones")
        );
    }
    private CollectionModel<EntityModel<ClasificacionResponseDTO>> crearColeccion(
            List<ClasificacionResponseDTO> clasificaciones) {
        List<EntityModel<ClasificacionResponseDTO>> modelos = clasificaciones.stream()
                .map(assembler::toModel)
                .toList();
        return CollectionModel.of(
                modelos,
                linkTo(methodOn(ClasificacionHateoasController.class).obtenerTodas()).withSelfRel()
        );
    }
}
