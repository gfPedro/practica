package com.practica.oee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
@ComponentScan(basePackageClasses = { PracticaBackendConfiguration.class })
public class PracticaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticaBackendApplication.class, args);
	}

}
