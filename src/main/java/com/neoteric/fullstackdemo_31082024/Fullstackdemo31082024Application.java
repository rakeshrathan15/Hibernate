package com.neoteric.fullstackdemo_31082024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.neoteric.fullstackdemo_31082024")

public class Fullstackdemo31082024Application {

	public static void main(String[] args) {
		SpringApplication.run(Fullstackdemo31082024Application.class, args);
	}

}
