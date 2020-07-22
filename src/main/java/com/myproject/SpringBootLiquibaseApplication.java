package com.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootLiquibaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLiquibaseApplication.class, args);
	}
}
