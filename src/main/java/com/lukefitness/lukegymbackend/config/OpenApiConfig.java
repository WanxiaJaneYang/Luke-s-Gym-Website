package com.lukefitness.lukegymbackend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Luke Gym Backend API")
                        .description("Luke Gym Backend API implemented with Spring Boot RESTful service and documented using springdoc-openapi and OpenAPI 3.0")
                        .version("1.0"))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "bearer-key",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")));
    }

    @Bean
    GroupedOpenApi adminApi() {
        String[] paths = {"/admin/**"};
        return GroupedOpenApi.builder().group("admin").pathsToMatch(paths)
                .build();
    }

    @Bean
    GroupedOpenApi trainerApi(){
        String[] paths = {"/trainer/{trainerId}/**","/send-verify-email/trainer/{trainerId}/**"};
        return GroupedOpenApi.builder().group("trainer").pathsToMatch(paths)
                .build();
    }

    @Bean
    GroupedOpenApi traineeApi(){
        String[] paths = {"/trainee/{traineeId}/**","/send-verify-email/trainee/{traineeId}/**"};
        return GroupedOpenApi.builder().group("trainee").pathsToMatch(paths)
                .build();
    }

    @Bean
    GroupedOpenApi loginApi(){
        String[] paths = {"/login/**","/register/**","/email/**","/verify/**"};
        return GroupedOpenApi.builder().group("public").pathsToMatch(paths)
                .build();
    }
//    @Operation(security = { @SecurityRequirement(name = "bearer-key") }) add to the protected api
}