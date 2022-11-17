package com.europcar.create_redit_card;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.Valid;

@SpringBootApplication
@Slf4j
public class CreateReditCardApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(CreateReditCardApplication.class, args);




	}

	@Override
	@Valid
	public void run(String... args) throws Exception {
		log.info("test");
	}


}

