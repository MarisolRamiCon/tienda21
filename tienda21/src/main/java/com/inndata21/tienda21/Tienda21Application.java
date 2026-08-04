package com.inndata21.tienda21;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Tienda21Application {

	public static void main(String[] args) {
		SpringApplication.run(Tienda21Application.class, args);
	}

}
