package com.trevisan.springboot.banking.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.trevisan.springboot.banking.filters.SearchCriteria;
import com.trevisan.springboot.banking.filters.TransactionSpecification;
import com.trevisan.springboot.banking.model.Account;
import com.trevisan.springboot.banking.model.Transaction;
import com.trevisan.springboot.banking.repository.TransactionRepository;

/**
 * @author Harlem Trevisan 
 */

@RestController
@RequestMapping("/api/accounts/{accountId}")
public class TransactionController {
	
	private final TransactionRepository repository;

	TransactionController(TransactionRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/Alltransactions")
	CollectionModel<EntityModel<Transaction>> all() {
		List<EntityModel<Transaction>> transactions = repository.findAll().stream()
				.map(transaction -> EntityModel.of(transaction,
						linkTo(methodOn(TransactionController.class).all()).withRel("Alltransactions")))
				.collect(Collectors.toList());
		return CollectionModel.of(transactions, linkTo(methodOn(TransactionController.class).all()).withSelfRel());
	}

	@GetMapping("/transactions")
	@ResponseBody
	List<Transaction> search(@PathVariable Long accountId) {
		Account account = new Account();
		account.setId(accountId);
		
		TransactionSpecification spec = 
				new TransactionSpecification(new SearchCriteria("account", ":", account));
		return repository.findAll(spec);
	}
}
