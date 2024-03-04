package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication
@ComponentScan(basePackages = {"com.app.*"})
@EntityScan(basePackages = {"com.app.*"})
@EnableJpaRepositories(basePackages = {"com.app.*"})
public class HMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(HMSApplication.class);
    }
}