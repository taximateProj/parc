package com.umc.taxisharing.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDTO {

	private String name;
	private String nickName;
	private String university;
	private String phoneNumber;
	private List<BankAccountDTO> bankAccounts;
	private String gender;
}