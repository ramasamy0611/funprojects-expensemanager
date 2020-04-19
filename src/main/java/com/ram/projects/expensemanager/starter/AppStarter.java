package com.ram.projects.expensemanager.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.ram.projects.expensemanager.common.Constants.ROOT_END_POINT;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = {"com.ram.projects.expensemanager.db.repo"})
@EntityScan(basePackages = {"com.ram.projects.expensemanager.db.entity"})
@ComponentScan(basePackages = {"com.ram.projects.expensemanager"})
public class AppStarter {
    public static void main(String[] args) {
        SpringApplication.run(AppStarter.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping(ROOT_END_POINT.concat("/**"))
//                        .allowedOrigins("http://localhost:4200")
//                        .allowedHeaders("Content-Type", "Access-Control-Allow-Methods", "Access-Control-Allow-Credentials", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers")
                        .allowedOrigins("*")
                        .allowedHeaders("*")
                        .allowedMethods("*");
            }
        };
    }
}




