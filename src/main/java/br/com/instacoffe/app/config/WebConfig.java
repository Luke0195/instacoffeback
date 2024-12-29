package br.com.instacoffe.app.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Match all paths
                .allowedOrigins("*") // Allow all origins
                .allowedMethods("*") // Allow specific HTTP methods
                .allowedHeaders("*");// Allow all headers
                // Allow credentials
    }
}
