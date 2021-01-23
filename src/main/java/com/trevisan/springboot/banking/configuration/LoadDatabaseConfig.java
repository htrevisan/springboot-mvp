package com.trevisan.springboot.banking.configuration;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.trevisan.springboot.banking.model.Account;
import com.trevisan.springboot.banking.model.Customer;
import com.trevisan.springboot.banking.model.Transaction;
import com.trevisan.springboot.banking.repository.AccountRepository;
import com.trevisan.springboot.banking.repository.CustomerRepository;
import com.trevisan.springboot.banking.repository.TransactionRepository;

/**
 * @author Harlem Trevisan 
 */

@Configuration
class LoadDatabaseConfig {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabaseConfig.class);
	
	@Bean @Order(1)
	CommandLineRunner populateAccounts(AccountRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Account(1l, 1035491l, new BigDecimal(0))));
			log.info("Preloading " + repository.save(new Account(2l, 1245686l, new BigDecimal(0))));
		};
	}

	@Bean @Order(2)
	CommandLineRunner populateCustomers(CustomerRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Customer(1l, "Harlem Trevisan", 54261586061l, true)));
			log.info("Preloading " + repository.save(new Customer(2l, "Trevisan Tecnologia ME", 21196481000122l, false)));							
		};
	}

}
