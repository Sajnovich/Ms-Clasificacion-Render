package com.biblioteca.clasificacion.hateoas;

import com.biblioteca.clasificacion.dto.ClasificacionResponseDTO;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ClasificacionModelAssemblerTest {

    private final Faker faker = new Faker();

    @Test
    void agregaEnlacesALaClasificacionYConsultaPorEdad() {
        ClasificacionModelAssembler assembler = new ClasificacionModelAssembler();
        ClasificacionResponseDTO clasificacion = new ClasificacionResponseDTO(
                1L,
                "PEGI 16",
                "Teen",
                16,
                faker.lorem().word(),
                faker.lorem().sentence()
        );

        EntityModel<ClasificacionResponseDTO> modelo = assembler.toModel(clasificacion);

        assertTrue(modelo.hasLink("self"));
        assertTrue(modelo.hasLink("clasificaciones"));
        assertTrue(modelo.hasLink("permitidas-para-edad"));
    }
}
