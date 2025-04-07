package com.thejoshini.curewell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class CurewellApplication {

	public static void main(String[] args) {
		System.out.println(LocalDate.now());
		SpringApplication.run(CurewellApplication.class, args);


	}

}
