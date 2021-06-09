package com.wechange.eseurekaserver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EsEurekaServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(EsEurekaServerApplication.class, args);
	}

}
