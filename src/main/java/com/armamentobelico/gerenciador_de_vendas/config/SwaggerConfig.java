package com.armamentobelico.gerenciador_de_vendas.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("gerenciador-de-vendas")
                .pathsToMatch("/api/**")
                .build();
    }
}
