package com.umc.taxisharing.converter;

import com.umc.taxisharing.domain.BankAccount;
import com.umc.taxisharing.domain.Member;
import com.umc.taxisharing.dto.BankAccountDTO;
import com.umc.taxisharing.dto.MemberRequestDTO;
import com.umc.taxisharing.dto.MemberResponse;

import java.util.List;

public class MemberConverter {

	public static MemberResponse.MemberResponseDTO toMemberResponseDTO(Member member) {
		return MemberResponse.MemberResponseDTO.builder()
			.memberId(member.getMemberId())
			.name(member.getName())
			.nickName(member.getNickName())
			.gender(String.valueOf(member.getGender()))
			.university(member.getUniversity())
			.phoneNumber(member.getPhoneNumber())
			.bankAccounts(member.getBankAccounts().stream()
				.map(MemberConverter::toBankAccountDTO)
				.toList())
			.build();
	}

	public static Member toMemberEntity(MemberRequestDTO requestDTO) {
		Member member = new Member();
		member.setName(requestDTO.getName());
		member.setNickName(requestDTO.getNickName());
		member.setUniversity(requestDTO.getUniversity());
		member.setPhoneNumber(requestDTO.getPhoneNumber());
		List<BankAccount> bankAccounts = requestDTO.getBankAccounts().stream()
			.map(MemberConverter::toBankAccountEntity)
			.toList();
		member.setBankAccounts(bankAccounts);

		return member;
	}

	public static BankAccountDTO toBankAccountDTO(BankAccount bankAccount) {
		return new BankAccountDTO(
			bankAccount.getId(),
			bankAccount.getBank(),
			bankAccount.getAccountNumber()
		);
	}

	public static BankAccount toBankAccountEntity(BankAccountDTO dto) {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setId(dto.getId());
		bankAccount.setBank(dto.getBank());
		bankAccount.setAccountNumber(dto.getAccountNumber());
		return bankAccount;
	}
}
