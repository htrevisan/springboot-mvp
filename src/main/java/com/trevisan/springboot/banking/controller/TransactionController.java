package com.trevisan.springboot.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.trevisan.springboot.banking.model.Transaction;
import com.trevisan.springboot.banking.services.TransactionService;

/**
 * @author Harlem Trevisan 
 */

@RestController
@RequestMapping("/api/accounts/{accountId}")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;	

	@GetMapping("/all-transactions")
	CollectionModel<EntityModel<Transaction>> all() {
		
		return transactionService.all();
		
	}

	@GetMapping("/transactions")
	@ResponseBody
	List<Transaction> search(@PathVariable Long accountId) {
		
		return transactionService.search(accountId);
		
	}
	
}
