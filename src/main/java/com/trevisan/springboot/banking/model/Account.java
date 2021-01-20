package com.trevisan.springboot.banking.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Harlem Trevisan 
 */

@Entity	// JPA annotation que diz que a classe é uma entidade e será persistida em uma tabela
public class Account {
	private @Id @GeneratedValue Long id; // JPA annotation que define uma PK que será gerada automaticamente
	private long number;
	private float balance;
	
//	@OneToMany(targetEntity=Transaction.class)  // Define a cardinalidade "um pra muitos" com a entidade Transaction 
//	private List<Transaction> transactionList = new ArrayList<>(); 
	
	public Account() { }
	
	public Account(long id, long number, float balance) {
		super();
		this.id = id;
		this.number = number;
		this.balance = balance;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Account account = (Account) o;
		return Objects.equals(id, account.id) &&
			Objects.equals(number, account.number) &&
			Objects.equals(balance, account.balance);
	}
	
	@Override
	public String toString() {
		return "Account{" +
			"id=" + id +
			", number='" + number + '\'' +
			", balance='" + balance + '\'' +		
			'}';
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
		
}
