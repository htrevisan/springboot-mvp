package com.trevisan.springboot.banking.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author Harlem Trevisan 
 */

@Entity	// JPA annotation que diz que a classe é uma entidade e será persistida em uma tabela
public class Customer {
	private @Id @GeneratedValue Long id; // JPA annotation que define uma PK que será gerada automaticamente
	private String name;
	private long taxNumber;
	private boolean isIndividualPerson;
	@OneToMany(targetEntity = Account.class) // Define a cardinalidade "um pra muitos" com a entidade Account
	private List<Account> accountList;
	
	public Customer() { }
	
	public Customer(long id, String name, long taxNumber, boolean isIndividualPerson) {
		super();
		this.id = id;
		this.name = name;
		this.taxNumber = taxNumber;
		this.isIndividualPerson = isIndividualPerson;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Customer customer = (Customer) o;
		return Objects.equals(id, customer.id) &&
			Objects.equals(name, customer.name) &&
			Objects.equals(taxNumber, customer.taxNumber);
	}
	
	@Override
	public String toString() {
		return "Account{" +
			"id=" + id +
			", name='" + name + '\'' +
			", taxNumber='" + taxNumber + '\'' +		
			'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(long taxNumber) {
		this.taxNumber = taxNumber;
	}

	public boolean isIndividualPerson() {
		return isIndividualPerson;
	}

	public void setIndividualPerson(boolean isIndividualPerson) {
		this.isIndividualPerson = isIndividualPerson;
	}

}
