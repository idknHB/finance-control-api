package com.renan.financecontrol.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
            title = "Finance Control API",
            version = "1.0",
            description = "Personal finance management API"
    )
)
public class OpenApiConfig {
}
