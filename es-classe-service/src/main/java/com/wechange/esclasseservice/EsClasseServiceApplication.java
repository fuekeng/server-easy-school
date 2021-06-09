package com.wechange.esclasseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.wechange.esclasseservice.repository.ClasseRepository;

@SpringBootApplication(scanBasePackages = { "com.wechange.easyschool", "com.wechange.easyschool.escommon.repository",
		"com.wechange.easyschool.escommon", "com.wechange.esclasseservice" })
@EnableConfigurationProperties
@EnableEurekaClient
@EnableMongoRepositories(basePackages = { "com.wechange.easyschool.escommon.repository",
		"com.wechange.esclasseservice.repository" })
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@EntityScan(basePackages = { "com.wechange.easyschool.esmodel",
		"com.wechange.easyschool.escommon.repository.UserDetailsRepository" })
@EnableSwagger2
public class EsClasseServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EsClasseServiceApplication.class, args);
	}

	@Autowired
	ClasseRepository classeeRepository;

	@Override
	public void run(String... args) throws Exception {
		/*
		 * classeeRepository.save(new Course("Informatique",1));
		 * classeeRepository.save(new Course("mathématique",3));
		 * classeeRepository.save(new Course("francais",4)); classeeRepository.save(new
		 * Course("physique",4)); classeeRepository.save(new Course("francais",4));
		 * 
		 */ 
	}

}
