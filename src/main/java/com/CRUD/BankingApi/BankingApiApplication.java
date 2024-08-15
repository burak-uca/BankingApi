package com.CRUD.BankingApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.CRUD.BankingApi.Model")
@EnableJpaRepositories(basePackages = "com.CRUD.BankingApi.Repository")
@ComponentScan(basePackages = {"com.CRUD.BankingApi.Controller", "com.CRUD.BankingApi.Service", "com.CRUD.BankingApi.Repository",
		"com.CRUD.BankingApi.Exception"})
public class BankingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingApiApplication.class, args);
	}
}
