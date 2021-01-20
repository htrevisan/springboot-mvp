package com.trevisan.springboot.banking.filters;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.trevisan.springboot.banking.model.Transaction;

public class TransactionSpecification implements Specification<Transaction> {

	private SearchCriteria criteria;
	
	public TransactionSpecification(SearchCriteria criteria) {
		this.criteria = criteria;
	}
	
	@Override
	public Predicate toPredicate(Root<Transaction> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		
		if (criteria.getOperation().equalsIgnoreCase(":")) {
			return builder.equal(root.get(criteria.getKey()), criteria.getValue());
		}
		
		return null;
	}

}
