package com.miproyecto.forohub.infra;

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
                                        .bearerFormat("JWT")))
                                .info(new Info()
                                        .title("ForoHub API")
                                        .description("API Rest de ForoHub que es una aplicación que implementa un foro de discusión completo. Los usuarios pueden crear tópicos, responder a ellos, gestionar su perfil y todo esto está protegido con autenticación basada en JWT (JSON Web Tokens).")
                                        .contact(new Contact()
                                                .name("Rafael-gg")
                                                .email("rafaelgimenez358@gmail.com"))
                                        .license(new License()
                                                .name("")
                                                .url("")));
    }

    public void message(){
        System.out.println("bearer is working");
    }
}