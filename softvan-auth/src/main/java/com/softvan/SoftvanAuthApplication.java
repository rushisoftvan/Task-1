package com.softvan;

import com.softvan.Repository.UserRepsitory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SoftvanAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftvanAuthApplication.class, args);
	}

}
