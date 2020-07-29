package org.man.fota.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider(InMemorySwaggerResourcesProvider defaultResourcesProvider) {
        return () -> Collections.singletonList(loadResource());
    }

    private SwaggerResource loadResource() {
        SwaggerResource wsResource = new SwaggerResource();
        wsResource.setName("Fota");
        wsResource.setSwaggerVersion("2.0");
        wsResource.setLocation("/swagger-config.yaml");
        return wsResource;
    }
}