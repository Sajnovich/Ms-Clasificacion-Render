package com.biblioteca.clasificacion.service;

import com.biblioteca.clasificacion.dto.ClasificacionRequestDTO;
import com.biblioteca.clasificacion.dto.ClasificacionResponseDTO;
import com.biblioteca.clasificacion.model.Clasificacion;
import com.biblioteca.clasificacion.repository.ClasificacionRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClasificacionServiceTest {

    @Mock
    private ClasificacionRepository clasificacionRepository;
    @InjectMocks
    private ClasificacionService clasificacionService;

    private final Faker faker = new Faker();

    @Test
    void crearGuardaLaClasificacionPegiYEsrb() {
        ClasificacionRequestDTO dto = new ClasificacionRequestDTO();
        dto.setPegi("PEGI 16");
        dto.setEsrb("Teen");
        dto.setEdadMinima(16);
        dto.setNombre(faker.lorem().word());
        dto.setDescripcion(faker.lorem().sentence());
        when(clasificacionRepository.save(any(Clasificacion.class))).thenAnswer(invocation -> {
            Clasificacion clasificacion = invocation.getArgument(0);
            clasificacion.setId(1L);
            return clasificacion;
        });

        ClasificacionResponseDTO resultado = clasificacionService.crear(dto);

        assertEquals(1L, resultado.id());
        assertEquals("PEGI 16", resultado.pegi());
        assertEquals("Teen", resultado.esrb());
        verify(clasificacionRepository).save(any(Clasificacion.class));
    }

    @Test
    void eliminarLanzaExcepcionCuandoLaClasificacionNoExiste() {
        Long id = faker.number().numberBetween(1L, 1000L);
        when(clasificacionRepository.existsById(id)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> clasificacionService.eliminar(id));
        verify(clasificacionRepository, never()).deleteById(id);
    }
}
