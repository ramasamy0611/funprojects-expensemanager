package com.ram.projects.expensemanager.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.ram.projects.expensemanager.common.Constants.ROOT_END_POINT;

@Configuration
public class ExMgrWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping(ROOT_END_POINT.concat("/**"))
                .allowedOrigins("http://localhost:4200","http://expmgr-frontend:4200")
                .allowedHeaders("Content-Type", "Access-Control-Allow-Methods", "Access-Control-Allow-Credentials", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers")
                .allowedMethods("*");
    }
}
