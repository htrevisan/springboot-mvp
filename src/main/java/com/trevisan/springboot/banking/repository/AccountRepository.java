package com.trevisan.springboot.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trevisan.springboot.banking.model.Account;

/**
 * @author Harlem Trevisan 
 */

public interface AccountRepository extends JpaRepository<Account, Long> {

}
