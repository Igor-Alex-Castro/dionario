package com.dionariao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.dionariao.model")
public class DionariaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DionariaoApplication.class, args);
	}

}
