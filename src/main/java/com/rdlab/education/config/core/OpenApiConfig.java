package com.rdlab.education.config.core;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Education API", version = "1.0", description = "Education API Documentation"))
public class OpenApiConfig {
}
