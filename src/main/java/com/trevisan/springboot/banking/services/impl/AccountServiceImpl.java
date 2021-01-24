package com.trevisan.springboot.banking.services.impl;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.trevisan.springboot.banking.exception.ObjectNotFoundException;
import com.trevisan.springboot.banking.model.Account;
import com.trevisan.springboot.banking.repository.AccountRepository;
import com.trevisan.springboot.banking.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private final AccountRepository repository;

	AccountServiceImpl(AccountRepository repository) {
		this.repository = repository;
	}

	@Override
	public CollectionModel<EntityModel<Account>> all() {

		List<EntityModel<Account>> accounts = repository.findAll().stream()
				.map(account -> EntityModel.of(account,
						linkTo(methodOn(AccountServiceImpl.class).one(account.getId())).withSelfRel(),
						linkTo(methodOn(AccountServiceImpl.class).all()).withRel("accounts")))
				.collect(Collectors.toList());

		return CollectionModel.of(accounts, linkTo(methodOn(AccountServiceImpl.class).all()).withSelfRel());
	}

	@Override
	public Account newAccount(Account newAccount) {
		return repository.save(newAccount);
	}
	
	@Override
	public EntityModel<Account> one(Long id) {

		Account account = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Account", id));
		
		return EntityModel.of(account,
				linkTo(methodOn(AccountServiceImpl.class).one(id)).withSelfRel(),
				linkTo(methodOn(AccountServiceImpl.class).all()).withRel("accounts"));
	}

	@Override
	public Account replaceAccount(Account newAccount, Long id) {

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
	
	@Override
	public BigDecimal balance(Long id) {

		Account account = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Account", id));
		
		return account.getBalance();
	}
	
	@Override
	public void deleteAccount(Long id) {
		repository.deleteById(id);
	}
}
