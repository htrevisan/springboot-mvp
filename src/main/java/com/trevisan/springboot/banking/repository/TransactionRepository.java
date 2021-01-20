package com.trevisan.springboot.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.trevisan.springboot.banking.model.Transaction;

/**
 * @author Harlem Trevisan 
 */

public interface TransactionRepository 
	extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> { }
