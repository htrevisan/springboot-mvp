package com.trevisan.springboot.banking.configuration;

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
	Account firstAccount = new Account(1l, 1l, 0.0f);
	
	@Bean @Order(1)
	CommandLineRunner populateAccounts(AccountRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(firstAccount));
		};
	}

	@Bean @Order(2)
	CommandLineRunner populateCustomers(CustomerRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Customer(1l, "Harlem Trevisan", 54261586061l, true)));
			log.info("Preloading " + repository.save(new Customer(2l, "Trevisan Tecnologia ME", 21196481000122l, false)));							
		};
	}
	@Bean @Order(3)
	CommandLineRunner populateTransactions(TransactionRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Transaction(1l, new Date(), 100l, firstAccount)));
			log.info("Preloading " + repository.save(new Transaction(2l, new Date(), 200l, firstAccount)));							
			log.info("Preloading " + repository.save(new Transaction(3l, new Date(), -23l, firstAccount)));
			log.info("Preloading " + repository.save(new Transaction(4l, new Date(),  92l, firstAccount)));
		};
	}
}
