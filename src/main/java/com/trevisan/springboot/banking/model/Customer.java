package com.trevisan.springboot.banking.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Customer {
	
	private @Id @GeneratedValue Long id; // JPA annotation que define uma PK que será gerada automaticamente
	private String name;
	private Long taxNumber;
	private boolean isIndividualPerson;
	@OneToMany(targetEntity = Account.class) // Define a cardinalidade "um pra muitos" com a entidade Account
	private List<Account> accountList;
	
	public Customer (Long id, String name, Long taxNumber, Boolean isIndividualPerson) {
		this.id = id;
		this.name = name;
		this.taxNumber = taxNumber;
		this.isIndividualPerson = isIndividualPerson;
	}
	
}
