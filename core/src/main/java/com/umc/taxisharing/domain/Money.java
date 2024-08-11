package com.umc.taxisharing.domain;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Money {

	@Field("amount")
	private BigDecimal amount;

	public Money() {}

	public Money(BigDecimal amount) {
		this.amount = amount;
	}
}