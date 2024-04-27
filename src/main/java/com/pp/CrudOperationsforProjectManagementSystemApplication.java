package com.pp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Library Management API", 
version = "1.0",
description = "An API for ProjectManagementSystem"),
servers = @Server(url = "http://localhost:8787/ProjectManagementSystem",
		            description = "API to Store Projects"))
public class CrudOperationsforProjectManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudOperationsforProjectManagementSystemApplication.class, args);
	}

}
