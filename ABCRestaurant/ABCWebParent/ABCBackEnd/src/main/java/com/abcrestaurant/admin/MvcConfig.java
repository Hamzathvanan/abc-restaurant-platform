package com.abcrestaurant.admin;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/images/user-photos/**")
                .addResourceLocations("file:ABCWebParent/ABCBackEnd/src/main/resources/static/images/user-photos/");

    }
}
