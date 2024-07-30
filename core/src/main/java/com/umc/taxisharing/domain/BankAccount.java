package com.umc.taxisharing.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document(collection = "bankAccounts")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BankAccount {

	@Field
	private String id; // MongoDB에서는 일반적으로 ID를 String으로 사용합니다.

	@Field
	private String bank;

	@Field
	private String accountNumber;

	@DBRef
	private Member member;
}