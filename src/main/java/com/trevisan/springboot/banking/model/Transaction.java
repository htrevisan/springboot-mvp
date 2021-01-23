package com.trevisan.springboot.banking.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class Transaction {
	
	private @Id @GeneratedValue Long id; // JPA annotation que define uma PK que será gerada automaticamente
	private Date date;
	private float value;	
	@ManyToOne(targetEntity=Account.class)
	private Account account;
		
}
