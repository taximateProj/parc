package com.umc.taxisharing.domain;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Money {

	@Field
	private BigDecimal amount;

	public Money(BigDecimal amount) {
		this.amount = amount;
	}
}