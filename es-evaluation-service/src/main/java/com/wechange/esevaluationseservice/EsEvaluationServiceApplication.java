package com.wechange.esevaluationseservice;

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

import com.wechange.easyschool.esmodel.entity.Student;
import com.wechange.esevaluationservice.repository.EvaluationRepository;

@SpringBootApplication(scanBasePackages = { "com.wechange.easyschool", "com.wechange.easyschool.escommon.repository",
		"com.wechange.easyschool.escommon", "com.wechange.esevaluationservice" })
@EnableConfigurationProperties
@EnableEurekaClient
@EnableMongoRepositories(basePackages = { "com.wechange.easyschool.escommon.repository",
		"com.wechange.esevaluationservice.repository" })
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@EntityScan(basePackages = { "com.wechange.easyschool.esmodel",
		"com.wechange.easyschool.escommon.repository.UserDetailsRepository" })
@EnableSwagger2
public class EsEvaluationServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EsEvaluationServiceApplication.class, args);
	}

	@Autowired
	EvaluationRepository stuedentRepository;

	@Override
	public void run(String... args) throws Exception {
		/*
		 * courseRepository.save(new Course("Informatique",1));
		 * courseRepository.save(new Course("mathématique",3));
		 * courseRepository.save(new Course("francais",4)); courseRepository.save(new
		 * Course("physique",4)); courseRepository.save(new Course("francais",4));
		 * 
		 */ 
	}

}
