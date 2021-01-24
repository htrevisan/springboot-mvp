package com.trevisan.springboot.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trevisan.springboot.banking.model.Customer;
import com.trevisan.springboot.banking.services.CustomerService;

/**
 * @author Harlem Trevisan 
 */

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;	

	@GetMapping("/api/customers")
	CollectionModel<EntityModel<Customer>> all() {

		return customerService.all();
		
	}
	
	@PostMapping("/api/customers")
	Customer newCustomer(@RequestBody Customer newCustomer) {
		
		return customerService.newCustomer(newCustomer);
		
	}

	@GetMapping("/api/customers/{id}")
	EntityModel<Customer> one(@PathVariable Long id) {

		return customerService.one(id);
		
	}
	
	@PutMapping("/api/customers/{id}")
	Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {

		return customerService.replaceCustomer(newCustomer, id);

	}

	@DeleteMapping("/api/customers/{id}")
	void deleteCustomer(@PathVariable Long id) {
		
		customerService.deleteCustomer(id);
		
	}
}
