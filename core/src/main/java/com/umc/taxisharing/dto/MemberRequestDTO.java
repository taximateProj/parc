package com.umc.taxisharing.dto;

import java.util.List;

import com.umc.taxisharing.domain.Gender;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDTO {

	@NonNull
	private String name;

	@NonNull
	private String nickName;
	private String university;

	@NonNull
	private String phoneNumber;

	@NonNull
	private List<BankAccountDTO> bankAccounts;

	@NonNull
	private Gender gender;
}