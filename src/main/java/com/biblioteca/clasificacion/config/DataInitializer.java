package com.biblioteca.clasificacion.config;

import org.springframework.beans.factory.annotation.Autowired;
import com.biblioteca.clasificacion.model.Clasificacion;
import com.biblioteca.clasificacion.repository.ClasificacionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private ClasificacionRepository clasificacionRepository;
    @Override
    public void run(String... args) {
        if (clasificacionRepository.count() > 0) {
            log.info(">>>> Clasificaciones ya cargadas. Se omite inicializacion");
            return;
        }
        log.info(">>>> Cargando clasificaciones iniciales...");
        clasificacionRepository.save(new Clasificacion(null, "PEGI 3", "ESRB E", 3,
                "Todo publico", "Contenido apto para la mayoria de usuarios."));
        clasificacionRepository.save(new Clasificacion(null, "PEGI 7", "ESRB E10+", 7,
                "Mayores de 7", "Puede incluir escenas o sonidos que asusten a ninos pequenos."));
        clasificacionRepository.save(new Clasificacion(null, "PEGI 12", "ESRB T", 12,
                "Adolescentes", "Puede incluir violencia moderada o lenguaje leve."));
        clasificacionRepository.save(new Clasificacion(null, "PEGI 16", "ESRB T/M", 16,
                "Mayores de 16", "Puede incluir violencia mas realista o temas maduros."));
        clasificacionRepository.save(new Clasificacion(null, "PEGI 18", "ESRB M/AO", 18,
                "Adultos", "Contenido para adultos, violencia intensa o temas sensibles."));
        log.info(">>>> {} clasificaciones cargadas OK.", clasificacionRepository.count());
    }
}
