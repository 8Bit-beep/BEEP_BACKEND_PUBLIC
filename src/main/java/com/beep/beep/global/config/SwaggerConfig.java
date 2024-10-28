package com.beep.beep.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        servers = {
                @Server(url = "https://beep.pe.kr", description = "kopis https 서버입니다."),
                @Server(url = "http://beep.pe.kr", description = "kopis http 서버입니다."),
                @Server(url = "http://43.201.252.15", description = "kopis local 서버입니다.")
        }
)
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Beep")
                .description("Beep API")
                .version("0.0.1");
    }

}
