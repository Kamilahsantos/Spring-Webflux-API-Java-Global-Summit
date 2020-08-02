package com.geekle.java.globalsummit.globalsummit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class GlobalsummitApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlobalsummitApplication.class, args);
	}

}
