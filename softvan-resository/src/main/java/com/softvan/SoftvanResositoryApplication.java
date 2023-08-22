package com.softvan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(scanBasePackages = "com.softvan")
public class SoftvanResositoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftvanResositoryApplication.class, args);
	}

}
