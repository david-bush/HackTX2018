package com.hacktx.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.hacktx")
public class HackTXApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackTXApplication.class, args);
	}
}
