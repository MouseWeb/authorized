package com.authorized.authorized.application;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mini autorizador ")
                        .description("Mini autorizador")
                        .contact(new Contact().name("Douglas Coelho").email("teste@teste.com"))
                        .version("1.0.0"));
    }
}
