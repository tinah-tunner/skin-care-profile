package com.tinah_tunner.skin.care.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing// Enable JPA Auditing to automatically populate created and modified dates
public class SkinCareProfileApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkinCareProfileApplication.class, args);
	}

}
