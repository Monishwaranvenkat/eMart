package com.eMart.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@SpringBootApplication

public class EMartApplication {

	public static void main(String[] args) {

		SpringApplication.run(EMartApplication.class, args);
	}

}
