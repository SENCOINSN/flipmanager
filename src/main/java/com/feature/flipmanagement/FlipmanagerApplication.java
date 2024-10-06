package com.feature.flipmanagement;

import lombok.extern.slf4j.Slf4j;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class FlipmanagerApplication implements CommandLineRunner{
	public static void main(String[] args) {
		
		SpringApplication.run(FlipmanagerApplication.class, args);
	}

	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("*******Application started*******");
		log.info("Credentials for access is  admin: pass123");
	}



}
