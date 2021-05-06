package com.stackroute.favourite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//annotation to make the class eureka enabled
@EnableEurekaClient
@SpringBootApplication
public class FavouriteApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavouriteApplication.class, args);
	}

}
