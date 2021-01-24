package com.trevisan.springboot.banking.services.impl;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.trevisan.springboot.banking.filters.SearchCriteria;
import com.trevisan.springboot.banking.filters.TransactionSpecification;
import com.trevisan.springboot.banking.model.Account;
import com.trevisan.springboot.banking.model.Transaction;
import com.trevisan.springboot.banking.repository.TransactionRepository;
import com.trevisan.springboot.banking.services.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private final TransactionRepository repository;
	
	TransactionServiceImpl(TransactionRepository repository) {
		this.repository = repository;
	}

	@Override
	public CollectionModel<EntityModel<Transaction>> all() {
		List<EntityModel<Transaction>> transactions = repository.findAll().stream()
				.map(transaction -> EntityModel.of(transaction,
						linkTo(methodOn(TransactionServiceImpl.class).all()).withRel("Alltransactions")))
				.collect(Collectors.toList());
		return CollectionModel.of(transactions, linkTo(methodOn(TransactionServiceImpl.class).all()).withSelfRel());
	}
	
	@Override
	public List<Transaction> search(Long accountId) {
		Account account = new Account();
		account.setId(accountId);
		
		TransactionSpecification spec = 
				new TransactionSpecification(new SearchCriteria("account", ":", account));
		return repository.findAll(spec);
	}

}
