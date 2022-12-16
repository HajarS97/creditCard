package com.europcar.create_redit_card;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@Slf4j
@EnableFeignClients
public class CreateReditCardApplication{


	public static void main(String[] args) {
		SpringApplication.run(CreateReditCardApplication.class, args);
	}



}

