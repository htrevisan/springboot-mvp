package com.trevisan.springboot.banking.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Formula;

/**
 * @author Harlem Trevisan 
 */

@Entity	// JPA annotation que diz que a classe é uma entidade e será persistida em uma tabela
public class Account {
	private @Id @GeneratedValue Long id; // JPA annotation que define uma PK que será gerada automaticamente
	private long number;
	
	@Formula("SELECT SUM(t.value) FROM Account a join Transaction t")	
	private Float balance;
	
	@OneToMany()  // Define a cardinalidade "um pra muitos" com a entidade Transaction
	private List<Transaction> transactions;
		
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

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
		
}
