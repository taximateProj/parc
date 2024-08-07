package com.umc.taxisharing.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponseDTO {
	private Long memberId;
	private String name;
	private String nickName;
	private String gender;
	private String university;
	private List<BankAccountDTO> bankAccounts;
}