package com.cairone.olingo.ext.jpa.query;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.expr.BooleanExpression;

public class DslQuery {

	private BooleanExpression booleanExpression = null;
	private OrderSpecifier<?>[] orderSpecifiers = null;
	
	public DslQuery(BooleanExpression booleanExpression, OrderSpecifier<?>[] orderSpecifiers) {
		super();
		this.booleanExpression = booleanExpression;
		this.orderSpecifiers = orderSpecifiers;
	}

	public BooleanExpression getBooleanExpression() {
		return booleanExpression;
	}

	public OrderSpecifier<?>[] getOrderSpecifiers() {
		return orderSpecifiers;
	}

	@Override
	public String toString() {
		return "DslQuery [booleanExpression=" + booleanExpression + ", orderSpecifiers="
				+ Arrays.toString(orderSpecifiers) + "]";
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> execute(QueryDslPredicateExecutor<?> queryDslPredicateExecutor, DslQuery dslQuery) {
		return dslQuery.getOrderSpecifiers() == null
				? (List<T>) queryDslPredicateExecutor.findAll(dslQuery.getBooleanExpression())
				: (List<T>) queryDslPredicateExecutor.findAll(dslQuery.getBooleanExpression(), dslQuery.getOrderSpecifiers());
	}
}
