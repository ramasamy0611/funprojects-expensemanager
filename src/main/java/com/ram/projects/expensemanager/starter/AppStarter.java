package com.ram.projects.expensemanager.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = {"com.ram.projects.expensemanager.db.repo"})
@EntityScan(basePackages = {"com.ram.projects.expensemanager.db.entity"})
@ComponentScan(basePackages = {"com.ram.projects.expensemanager"})
public class AppStarter {
  public static void main(String[] args) {
    SpringApplication.run(AppStarter.class, args);
  }
}
