package com.trevisan.springboot.banking.services;

import java.math.BigDecimal;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import com.trevisan.springboot.banking.model.Account;

public interface AccountService {

	public CollectionModel<EntityModel<Account>> all();
	
	public Account newAccount(Account newAccount);
	
	public EntityModel<Account> one(Long id);
	
	public Account replaceAccount(Account newAccount, Long id);
	
	public BigDecimal balance(Long id);
	
	public void deleteAccount(Long id);
	
}
