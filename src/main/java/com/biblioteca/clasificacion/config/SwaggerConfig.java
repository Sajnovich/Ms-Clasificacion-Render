package com.biblioteca.clasificacion.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI clasificacionOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("API Ms-Clasificacion")
                .version("1.0")
                .description("Gestion de clasificaciones PEGI y ESRB."));
    }
}
