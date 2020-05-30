package com.ram.projects.expensemanager.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@Configuration
@EnableCaching
@EnableJpaRepositories(basePackages = {"com.ram.projects.expensemanager.db.repo"})
@EntityScan(basePackages = {"com.ram.projects.expensemanager.db.entity"})
@ComponentScan(basePackages = {"com.ram.projects.expensemanager"})
public class AppStarter {
  public static void main(String[] args) {
//    TimeZone.setDefault(TimeZone.getTimeZone("IST"));
    SpringApplication.run(AppStarter.class, args);
  }
}
