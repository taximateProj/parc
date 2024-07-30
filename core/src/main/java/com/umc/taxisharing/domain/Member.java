package com.umc.taxisharing.domain;

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
	private String email;

	@Field
	private Gender gender;  // enum 필드 추가
}

enum Gender {
	MALE,
	FEMALE
}