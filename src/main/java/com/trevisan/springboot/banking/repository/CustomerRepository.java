package com.trevisan.springboot.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trevisan.springboot.banking.model.Customer;

/**
 * @author Harlem Trevisan 
 */

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
