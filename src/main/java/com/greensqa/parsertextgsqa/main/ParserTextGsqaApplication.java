package com.greensqa.parsertextgsqa.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.greensqa.parsertextgsqa")
public class ParserTextGsqaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParserTextGsqaApplication.class, args);
	}

}
