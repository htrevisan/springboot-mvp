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
import com.trevisan.springboot.banking.repository.AccountRepository;

/**
 * @author Harlem Trevisan 
 */

@RestController
public class AccountController {
	
	private final AccountRepository repository;

	AccountController(AccountRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/api/accounts")
	CollectionModel<EntityModel<Account>> all() {

		List<EntityModel<Account>> accounts = repository.findAll().stream()
				.map(account -> EntityModel.of(account,
						linkTo(methodOn(AccountController.class).one(account.getId())).withSelfRel(),
						linkTo(methodOn(AccountController.class).all()).withRel("accounts")))
				.collect(Collectors.toList());

		return CollectionModel.of(accounts, linkTo(methodOn(AccountController.class).all()).withSelfRel());
	}

	@PostMapping("/api/accounts")
	Account newAccount(@RequestBody Account newAccount) {
		return repository.save(newAccount);
	}

	@GetMapping("/api/accounts/{id}")
	EntityModel<Account> one(@PathVariable Long id) {

		Account account = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Account", id));
		
		return EntityModel.of(account,
				linkTo(methodOn(AccountController.class).one(id)).withSelfRel(),
				linkTo(methodOn(AccountController.class).all()).withRel("accounts"));
	}

	@PutMapping("/api/accounts/{id}")
	Account replaceAccount(@RequestBody Account newAccount, @PathVariable Long id) {

		return repository.findById(id)
				.map(account -> {
					account.setNumber(newAccount.getNumber());
					account.setBalance(newAccount.getBalance());
					return repository.save(account);
				})
				.orElseGet(() -> {
					newAccount.setId(id);
					return repository.save(newAccount);
				});
	}
	
	@GetMapping("/api/accounts/{id}/balance")
	Float balance(@PathVariable Long id) {

		Account account = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Account", id));
		
		return account.getBalance();
	}

	@DeleteMapping("/api/accounts/{id}")
	void deleteAccount(@PathVariable Long id) {
		repository.deleteById(id);
	}
}