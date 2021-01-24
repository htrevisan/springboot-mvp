package com.trevisan.springboot.banking.services.impl;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.trevisan.springboot.banking.exception.ObjectNotFoundException;
import com.trevisan.springboot.banking.model.Customer;
import com.trevisan.springboot.banking.repository.CustomerRepository;
import com.trevisan.springboot.banking.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private final CustomerRepository repository;

	CustomerServiceImpl(CustomerRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public CollectionModel<EntityModel<Customer>> all() {

		List<EntityModel<Customer>> customers = repository.findAll().stream()
				.map(customer -> EntityModel.of(customer,
						linkTo(methodOn(CustomerServiceImpl.class).one(customer.getId())).withSelfRel(),
						linkTo(methodOn(CustomerServiceImpl.class).all()).withRel("customers")))
				.collect(Collectors.toList());

		return CollectionModel.of(customers, linkTo(methodOn(CustomerServiceImpl.class).all()).withSelfRel());
	}
	
	@Override
	public Customer newCustomer(Customer newCustomer) {
		return repository.save(newCustomer);
	}

	@Override
	public EntityModel<Customer> one(Long id) {

		Customer customer = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Customer", id));

		return EntityModel.of(customer,
				linkTo(methodOn(CustomerServiceImpl.class).one(id)).withSelfRel(),
				linkTo(methodOn(CustomerServiceImpl.class).all()).withRel("customers"));
	}
	
	@Override
	public Customer replaceCustomer(Customer newCustomer, Long id) {

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

	@Override
	public void deleteCustomer(Long id) {
		repository.deleteById(id);
	}
	
}
