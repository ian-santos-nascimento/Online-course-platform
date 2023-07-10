package com.example.hotmart.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customAPI(){
        return new OpenAPI().info(
                new Info().title("Online platform courses build with  Java 18 and SpringBoot 3")
                        .version("v1")
                        .description("This is a monolithic application focused on building a courses platform using GridFS to store the video files .")
                        .termsOfService("© 2023- Ian Santos Nascimento . All rights reserved")
        );
    }
}
