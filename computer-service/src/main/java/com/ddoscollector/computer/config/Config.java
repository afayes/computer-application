package com.ddoscollector.computer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class Config {

    @Value("#{'${allowedOrigins}'.split(',')}")
    private List<String> allowedOrigins;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(final CorsRegistry registry) {
                final String[] allowedOriginsArray = allowedOrigins.toArray(new String[allowedOrigins.size()]);
                registry.addMapping("/computers/**").allowedOrigins(allowedOriginsArray).allowedMethods("*");
            }
        };
    }
}
