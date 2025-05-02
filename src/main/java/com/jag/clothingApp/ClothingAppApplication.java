package com.jag.clothingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class ClothingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClothingAppApplication.class, args);
	}

}
