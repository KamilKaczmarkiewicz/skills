package com.project.skill.common.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {
        var server = new Server();
        server.setUrl("/");
        return new OpenAPI().servers(List.of(server));
    }
}
