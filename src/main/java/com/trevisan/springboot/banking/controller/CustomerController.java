package com.trevisan.springboot.banking.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trevisan.springboot.banking.ObjectNotFoundException;
import com.trevisan.springboot.banking.model.Account;
import com.trevisan.springboot.banking.model.Customer;
import com.trevisan.springboot.banking.repository.CustomerRepository;

/**
 * @author Harlem Trevisan 
 */

@RestController
public class CustomerController {
	
	private final CustomerRepository repository;

	CustomerController(CustomerRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/api/customers")
	CollectionModel<EntityModel<Customer>> all() {

		List<EntityModel<Customer>> customers = repository.findAll().stream()
				.map(customer -> EntityModel.of(customer,
						linkTo(methodOn(CustomerController.class).one(customer.getId())).withSelfRel(),
						linkTo(methodOn(CustomerController.class).all()).withRel("customers")))
				.collect(Collectors.toList());

		return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).all()).withSelfRel());
	}
	
	@PostMapping("/api/customers")
	Customer newCustomer(@RequestBody Customer newCustomer) {
		return repository.save(newCustomer);
	}

	@GetMapping("/api/customers/{id}")
	EntityModel<Customer> one(@PathVariable Long id) {

		Customer customer = repository.findById(id) //
				.orElseThrow(() -> new ObjectNotFoundException("Customer", id));

		return EntityModel.of(customer, //
				linkTo(methodOn(CustomerController.class).one(id)).withSelfRel(),
				linkTo(methodOn(CustomerController.class).all()).withRel("customers"));
	}
	
	@PutMapping("/api/customers/{id}")
	Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {

		return repository.findById(id)
				.map(customer -> {
					customer.setName(newCustomer.getName());
					customer.setTaxNumber(newCustomer.getTaxNumber());
					customer.setIndividualPerson(newCustomer.isIndividualPerson());
					return repository.save(customer);
				}) 
				.orElseGet(() -> {
					newCustomer.setId(id);
					return repository.save(newCustomer);
				});
	}

	@DeleteMapping("/api/customers/{id}")
	void deleteCustomer(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
