package com.trevisan.springboot.banking.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Harlem Trevisan 
 */

@Entity	// JPA annotation que diz que a classe é uma entidade e será persistida em uma tabela
public class Transaction {
	private @Id @GeneratedValue Long id; // JPA annotation que define uma PK que será gerada automaticamente
	private Date date;
	private float value;	
	@ManyToOne(targetEntity=Account.class)
	private Account account;
	
	public Transaction() { }
	
	public Transaction(long id, Date date, float value, Account account) {
		super();
		this.id = id;
		this.date = date;
		this.value = value;
		this.account = account;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Transaction transaction = (Transaction) o;
		return Objects.equals(id, transaction.id) &&
			Objects.equals(id, transaction.id) &&
			Objects.equals(value, transaction.value);
	}
	
	@Override
	public String toString() {
		return "Account{" +
			"id=" + id +
			", date='" + date + '\'' +
			", value='" + value + '\'' +		
			'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
		
}
