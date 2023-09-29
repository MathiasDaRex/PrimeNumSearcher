package com.mathias.primenum;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class PrimeNumSearcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimeNumSearcherApplication.class, args);
	}

}
