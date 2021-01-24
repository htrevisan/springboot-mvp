package com.trevisan.springboot.banking.services;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import com.trevisan.springboot.banking.model.Transaction;

public interface TransactionService {

	public CollectionModel<EntityModel<Transaction>> all();
	
	public List<Transaction> search(Long accountId);

}
