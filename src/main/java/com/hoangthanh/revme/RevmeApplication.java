package com.hoangthanh.revme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.hoangthanh.revme.repository")
public class RevmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevmeApplication.class, args);
	}

}
