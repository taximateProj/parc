package com.umc.taxisharing.domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "members")
public class Member {

	@Field
	private String id;

	@Field
	private String name;

	@Field
	private String nickName;

	@Field
	private Gender gender;  // enum 필드 추가

	@Field
	private String university;

	@Field
	private List<BankAccount> bankAccounts;

}

enum Gender {
	MALE,
	FEMALE
}