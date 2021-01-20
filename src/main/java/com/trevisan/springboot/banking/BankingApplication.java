package com.trevisan.springboot.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Harlem Trevisan 
 */

@SpringBootApplication
/**
 * auto-configuration
 * O Spring Boot provê as configurações básicas necessárias para os frameworks que estão no CLASSPATH
 * Ex: JacksonAutoConfiguration (processador JSON)
 */
public class BankingApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(BankingApplication.class, args);
		
	}
	
}
