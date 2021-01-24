package com.trevisan.springboot.banking.services;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import com.trevisan.springboot.banking.model.Customer;

public interface CustomerService {
	
	public CollectionModel<EntityModel<Customer>> all();

	public Customer newCustomer(Customer newCustomer);

	public EntityModel<Customer> one(Long id);

	public Customer replaceCustomer(Customer newCustomer, Long id);

	public void deleteCustomer(Long id);

}
