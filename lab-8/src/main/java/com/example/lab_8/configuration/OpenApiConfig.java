package com.example.lab_8.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Інтернет-розсилка API")
                        .version("1.0")
                        .description("API для управління авторами, клієнтами, матеріалами (статтями) та підписками.")
                        .contact(new Contact()
                                .name("Diana Sliesarchuk")
                                .email("sliesarchuk.diana@chnu.edu.ua")
                                .url("https://github.com/sliesarchukDiana"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")))
                .servers(Collections.singletonList(new Server().url("/").description("Base URL")));
    }

    @Bean
    public GroupedOpenApi authorApi() {
        return GroupedOpenApi.builder()
                .group("1. Authors Module")
                .pathsToMatch("/api/authors/**")
                .build();
    }

    @Bean
    public GroupedOpenApi materialApi() {
        return GroupedOpenApi.builder()
                .group("2. Materials (Articles) Module")
                .pathsToMatch("/api/materials/**")
                .build();
    }

    @Bean
    public GroupedOpenApi clientApi() {
        return GroupedOpenApi.builder()
                .group("3. Clients Module")
                .pathsToMatch("/api/clients/**")
                .build();
    }

    @Bean
    public GroupedOpenApi subscriptionApi() {
        return GroupedOpenApi.builder()
                .group("4. Subscriptions Module")
                .pathsToMatch("/api/subscriptions/**")
                .build();
    }

    @Bean
    public GroupedOpenApi catalogueApi() {
        return GroupedOpenApi.builder()
                .group("5. Catalogue & Keywords Module")
                .pathsToMatch("/api/sections/**", "/api/keywords/**")
                .build();
    }
}