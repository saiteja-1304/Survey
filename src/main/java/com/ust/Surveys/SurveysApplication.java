package com.ust.Surveys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SurveysApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurveysApplication.class, args);
	}

}
