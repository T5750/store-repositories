package com.backyard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class BackyardApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(BackyardApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(BackyardApplication.class);
	}
}
// java -jar backyard-plus-1.1.0.jar
// http://localhost:8080/backyard-plus
// http://localhost:8080/backyard-plus/swagger/index.html