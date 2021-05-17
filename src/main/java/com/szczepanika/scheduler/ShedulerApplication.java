package com.szczepanika.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ShedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShedulerApplication.class, args);
	}


	private static final Logger LOG = LoggerFactory.getLogger(ShedulerApplication.class);


}
