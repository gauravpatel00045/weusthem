package com.contactapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ContactApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactApiApplication.class, args);
	}
	
	/*
	 * ModelMapper Configuration
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
