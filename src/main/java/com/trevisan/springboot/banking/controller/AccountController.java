package com.trevisan.springboot.banking.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trevisan.springboot.banking.model.Account;
import com.trevisan.springboot.banking.services.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author Harlem Trevisan 
 */

@Api(value="Conjunto de operações relacionadas à conta.")
@RestController
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@ApiIgnore
	@GetMapping("/api/accounts")
	CollectionModel<EntityModel<Account>> all() {

		return accountService.all();

	}

	@ApiOperation(value="Cria uma nova conta.")
	@PostMapping("/api/accounts")
	Account newAccount(@RequestBody Account newAccount) {
		
		return accountService.newAccount(newAccount);
		
	}
	
	@ApiOperation(value="Retorna os dados da conta correspondente ao identificador informado.")
	@GetMapping("/api/accounts/{id}")
	EntityModel<Account> one(@PathVariable Long id) {

		return accountService.one(id);
		
	}

	@ApiOperation(value="Atualiza dados de uma conta existente.")
	@PutMapping("/api/accounts/{id}")
	Account replaceAccount(@RequestBody Account newAccount, @PathVariable Long id) {

		return accountService.replaceAccount(newAccount, id);
		
	}
	
	@ApiOperation(value="Retorna o saldo da conta conforme o identificador informado.")
	@GetMapping("/api/accounts/{id}/balance")
	BigDecimal balance(@PathVariable Long id) {

		return accountService.balance(id);
		
	}
	
	@ApiIgnore
	@DeleteMapping("/api/accounts/{id}")
	void deleteAccount(@PathVariable Long id) {
		
		accountService.deleteAccount(id);
		
	}
}
