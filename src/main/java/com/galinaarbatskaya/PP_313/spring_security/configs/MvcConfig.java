package com.galinaarbatskaya.PP_313.spring_security.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class MvcConfig {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user").setViewName("myUser");
        registry.addViewController("/admin").setViewName("allUsers");
    }
}
