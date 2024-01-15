package com.customermanagementservice.config;

import java.util.Arrays;

import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    private String baseUrl = "http://localhost:8082";

    @Bean
    public OpenAPI customOpenAPI() {
        var server = new io.swagger.v3.oas.models.servers.Server().url(baseUrl);
        return new OpenAPI().servers(Arrays.asList(server)).info(new Info().title("Customer Management Service Api Doc")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    static {
        SpringDocUtils.getConfig().addRestControllers(SwaggerConfig.class);
    }
}


