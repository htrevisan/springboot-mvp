package com.trevisan.springboot.banking.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Formula;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Harlem Trevisan 
 */

@Data  // Getters and setters, equals(), hashCode() e toString() gerados com esta annotation - Lombok
@AllArgsConstructor // Construtor com todos os argumentos da entidade - Lombok
@NoArgsConstructor  // Construtor sem argumentos - Lombok
@Entity	// JPA annotation que diz que a classe é uma entidade e será persistida em uma tabela
public class Account {
	
	private @Id @GeneratedValue Long id; // JPA annotation que define uma PK que será gerada automaticamente
	private Long number;
	
	@Formula("SELECT SUM(t.value) FROM Account a join Transaction t")	
	private BigDecimal balance;
	
	@OneToMany()  // Define a cardinalidade "um pra muitos" com a entidade Transaction
	private List<Transaction> transactions;	

	public Account(Long id, Long number, BigDecimal balance) {
		this.id = id;
		this.number = number;
		this.balance = balance;
	}
}
