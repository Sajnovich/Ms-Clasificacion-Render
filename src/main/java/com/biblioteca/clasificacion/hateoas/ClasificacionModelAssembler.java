package com.biblioteca.clasificacion.hateoas;

import com.biblioteca.clasificacion.dto.ClasificacionResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClasificacionModelAssembler
        implements RepresentationModelAssembler<ClasificacionResponseDTO, EntityModel<ClasificacionResponseDTO>> {

    @Override
    public EntityModel<ClasificacionResponseDTO> toModel(ClasificacionResponseDTO clasificacion) {
        return EntityModel.of(
                clasificacion,
                linkTo(methodOn(ClasificacionHateoasController.class)
                        .obtenerPorId(clasificacion.id())).withSelfRel(),
                linkTo(methodOn(ClasificacionHateoasController.class)
                        .obtenerTodas()).withRel("clasificaciones"),
                linkTo(methodOn(ClasificacionHateoasController.class)
                        .obtenerPermitidasParaEdad(clasificacion.edadMinima())).withRel("permitidas-para-edad")
        );
    }
}
