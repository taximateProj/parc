package com.umc.taxisharing.dto;

import java.util.List;

import com.umc.taxisharing.domain.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class MemberResponse {
	@Getter
	@Setter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MemberResponseDTO {
		Long memberId;
		String name;
		String nickName;
		Gender gender;
		String university;
		String phoneNumber;
		List<BankAccountDTO> bankAccounts;
	}
}