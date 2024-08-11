package com.umc.taxisharing.converter;

import com.umc.taxisharing.domain.BankAccount;
import com.umc.taxisharing.domain.Gender;
import com.umc.taxisharing.domain.Member;
import com.umc.taxisharing.dto.BankAccountDTO;
import com.umc.taxisharing.dto.MemberRequestDTO;
import com.umc.taxisharing.dto.MemberResponse;

import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

	/**
	 * Member 엔티티를 MemberResponseDTO로 변환
	 * @param member 변환할 Member 엔티티
	 * @return 변환된 MemberResponseDTO
	 */
	public static MemberResponse.MemberResponseDTO toMemberResponseDTO(Member member) {
		return MemberResponse.MemberResponseDTO.builder()
			.memberId(member.getMemberId())
			.name(member.getName())
			.nickName(member.getNickName())
			.gender(member.getGender()) // Gender enum 설정
			.university(member.getUniversity())
			.phoneNumber(member.getPhoneNumber())
			.bankAccounts(member.getBankAccounts().stream()
				.map(MemberConverter::toBankAccountDTO)
				.collect(Collectors.toList())) // BankAccount 엔티티를 DTO로 변환
			.build();
	}

	/**
	 * MemberRequestDTO를 Member 엔티티로 변환
	 * @param requestDTO 변환할 MemberRequestDTO
	 * @return 변환된 Member 엔티티
	 */
	public static Member toMemberEntity(MemberRequestDTO requestDTO) {
		Member member = new Member();
		member.setName(requestDTO.getName());
		member.setNickName(requestDTO.getNickName());
		member.setUniversity(requestDTO.getUniversity());
		member.setPhoneNumber(requestDTO.getPhoneNumber());
		member.setGender(requestDTO.getGender()); // Gender enum 설정

		// BankAccountDTO 리스트를 BankAccount 엔티티 리스트로 변환
		List<BankAccount> bankAccounts = requestDTO.getBankAccounts().stream()
			.map(MemberConverter::toBankAccountEntity)
			.collect(Collectors.toList());
		member.setBankAccounts(bankAccounts); // Member 엔티티에 BankAccount 설정

		return member;
	}

	/**
	 * BankAccount 엔티티를 BankAccountDTO로 변환
	 * @param bankAccount 변환할 BankAccount 엔티티
	 * @return 변환된 BankAccountDTO
	 */
	public static BankAccountDTO toBankAccountDTO(BankAccount bankAccount) {
		return new BankAccountDTO(
			bankAccount.getId(),
			bankAccount.getBank(),
			bankAccount.getAccountNumber()
		);
	}

	/**
	 * BankAccountDTO를 BankAccount 엔티티로 변환
	 * @param dto 변환할 BankAccountDTO
	 * @return 변환된 BankAccount 엔티티
	 */
	public static BankAccount toBankAccountEntity(BankAccountDTO dto) {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setId(dto.getId());
		bankAccount.setBank(dto.getBank());
		bankAccount.setAccountNumber(dto.getAccountNumber());
		return bankAccount;
	}
}
