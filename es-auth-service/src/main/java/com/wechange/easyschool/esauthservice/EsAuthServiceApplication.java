package com.wechange.easyschool.esauthservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.wechange.easyschool.esauthservice.repository.UserRepository;
import com.wechange.easyschool.esauthservice.service.UserUpdateService;
import com.wechange.easyschool.esmodel.entity.user.EnumAuthority;
import com.wechange.easyschool.esmodel.entity.user.User;

@SpringBootApplication(scanBasePackages={"com.wechange.easyschool","com.wechange.easyschool.escommon.repository"})
@EnableEurekaClient
@EnableMongoRepositories(basePackages = {"com.wechange.easyschool.escommon.repository","com.wechange.easyschool.esauthservice.repository"} )
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EntityScan(basePackages = {"com.wechange.easyschool.esmodel"})
@ComponentScan(basePackages = "com.wechange.easyschool")
@EnableConfigurationProperties()

public class EsAuthServiceApplication implements CommandLineRunner {
	
	  @Autowired UserUpdateService userService;
	 
	public static void main(String[] args) {  
		ConfigurableApplicationContext ctx=SpringApplication.run(EsAuthServiceApplication.class, args);	
	}
	
	/**
	 *
	 */
	public void run(String... args) throws Exception {
		
		/*
		 * User user=new User(); user.addRole(EnumAuthority.ADMIN);
		 * user.setUsername("fuekeng"); user.setPseudo("fuekeng");
		 * user.setLastName("fuekeng"); user.setEmail("fuekengbilly@gmail.com");
		 * userService.registerUser(user, "billy");
		 * System.out.println("utilisateur enregistré");
		 */
		    
		  
	}

}
