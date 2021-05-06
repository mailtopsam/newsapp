package com.stackroute.APIGatewayforNews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
//annotation makes the Class as Zuul Proxy
@EnableZuulProxy

public class ApiGatewayForNewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayForNewsApplication.class, args);
	}

}
