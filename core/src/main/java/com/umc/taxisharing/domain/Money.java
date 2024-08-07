package com.umc.taxisharing.domain;

import java.math.BigDecimal;

import jakarta.persistence.Embeddable;

@Embeddable
public class Money {

	private BigDecimal amount;

}