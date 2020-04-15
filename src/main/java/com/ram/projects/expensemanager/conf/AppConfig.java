package com.ram.projects.expensemanager.conf;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableJpaRepositories(basePackages = {"com.ram.projects.expensemanager.db.repo"})
@EntityScan(basePackages = {"com.ram.projects.expensemanager.db.entity"})
@ComponentScan(basePackages = {"com.ram.projects.expensemanager"})
public class AppConfig {
}
