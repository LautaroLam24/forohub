package com.aluralatam.forohub.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                )
                .info(new Info()
                        .title("ForoHub API")
                        .description("""
                                API REST desarrollada con Spring Boot para la gestión de tópicos de un foro.
                                
                                Permite realizar operaciones CRUD sobre tópicos:
                                - Crear tópicos
                                - Listar tópicos
                                - Consultar detalle de un tópico
                                - Actualizar tópicos
                                - Eliminar tópicos
                                
                                La API utiliza autenticación basada en JWT mediante Spring Security.
                                
                                Proyecto desarrollado como parte del challenge ForoHub del programa Oracle Next Education (Alura Latam).
                                """)
                        .contact(new Contact()
                                .name("Lautaro Lamaita")
                                .email("lautarolamaita@gmail.com")
                                .url("https://github.com/LautaroLam24")
                        )
                        .license(new License()
                                .name("Licencia MIT")
                                .url("https://opensource.org/licenses/MIT")
                        )
                );
    }
}