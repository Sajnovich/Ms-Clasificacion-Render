package com.biblioteca.clasificacion.repository;

import com.biblioteca.clasificacion.model.Clasificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClasificacionRepository extends JpaRepository<Clasificacion, Long> {

    List<Clasificacion> findByEdadMinima(Integer edadMinima);

    List<Clasificacion> findByEdadMinimaLessThanEqual(Integer edad);
}
