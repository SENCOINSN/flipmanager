package com.feature.flipmanagement;

import lombok.extern.slf4j.Slf4j;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class FlipmanagerApplication {

	public static void main(String[] args) {
		log.info("*******Application started*******");
		log.info("Credentials for access is  admin: pass123");
		SpringApplication.run(FlipmanagerApplication.class, args);
	}

	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}



}
