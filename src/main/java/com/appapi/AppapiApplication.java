package com.appapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.appapi")
public class AppapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppapiApplication.class, args);
	}
}
