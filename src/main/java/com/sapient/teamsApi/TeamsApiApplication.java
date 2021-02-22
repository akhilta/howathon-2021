package com.sapient.teamsApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.sapient.teamsApi")
public class TeamsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamsApiApplication.class, args);
	}

}
