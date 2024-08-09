package com.umc.taxisharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.umc.taxisharing.domain.BankAccount;
import com.umc.taxisharing.domain.Gender;
import com.umc.taxisharing.domain.Member;
import com.umc.taxisharing.dto.MemberRequestDTO;
import com.umc.taxisharing.dto.MemberResponse;
import com.umc.taxisharing.repository.MemberRepository;
import com.umc.common.error.exception.CustomException;
import com.umc.common.error.code.CommonErrorCode;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Transactional
	public MemberResponse.MemberResponseDTO createMember(MemberRequestDTO memberRequestDTO) {
		Member member = new Member();
		member.setName(memberRequestDTO.getName());
		member.setNickName(memberRequestDTO.getNickName());
		member.setUniversity(memberRequestDTO.getUniversity());
		member.setGender(Gender.valueOf(memberRequestDTO.getGender()));

		List<BankAccount> bankAccounts = memberRequestDTO.getBankAccounts().stream()
			.map(dto -> {
				BankAccount bankAccount = new BankAccount();
				bankAccount.setBank(dto.getBank());
				bankAccount.setAccountNumber(dto.getAccountNumber());
				return bankAccount;
			})
			.toList();

		member.setBankAccounts(bankAccounts);

		Member savedMember = memberRepository.save(member);
		return MemberResponse.MemberResponseDTO.builder()
			.memberId(savedMember.getMemberId())
			.name(savedMember.getName())
			.nickName(savedMember.getNickName())
			.gender(savedMember.getGender().name())
			.university(savedMember.getUniversity())
			.build();
	}

	@Transactional(readOnly = true)
	public MemberResponse.MemberResponseDTO getMember(Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new CustomException(CommonErrorCode.MEMBER_NOT_FOUND));

		return MemberResponse.MemberResponseDTO.builder()
			.memberId(member.getMemberId())
			.name(member.getName())
			.nickName(member.getNickName())
			.gender(member.getGender().name())
			.university(member.getUniversity())
			.build();
	}

	@Transactional
	public MemberResponse.MemberResponseDTO updateMember(Long memberId, MemberRequestDTO memberRequestDTO) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new CustomException(CommonErrorCode.MEMBER_NOT_FOUND));

		member.setName(memberRequestDTO.getName());
		member.setNickName(memberRequestDTO.getNickName());
		member.setUniversity(memberRequestDTO.getUniversity());
		member.setGender(Gender.valueOf(memberRequestDTO.getGender()));

		List<BankAccount> bankAccounts = memberRequestDTO.getBankAccounts().stream()
			.map(dto -> {
				BankAccount bankAccount = new BankAccount();
				bankAccount.setBank(dto.getBank());
				bankAccount.setAccountNumber(dto.getAccountNumber());
				return bankAccount;
			})
			.toList();

		member.setBankAccounts(bankAccounts);

		Member updatedMember = memberRepository.save(member);
		return MemberResponse.MemberResponseDTO.builder()
			.memberId(updatedMember.getMemberId())
			.name(updatedMember.getName())
			.nickName(updatedMember.getNickName())
			.gender(updatedMember.getGender().name())
			.university(updatedMember.getUniversity())
			.build();
	}

	@Transactional
	public void deleteMember(Long memberId) {
		if (!memberRepository.existsById(memberId)) {
			throw new CustomException(CommonErrorCode.MEMBER_NOT_FOUND);
		}
		memberRepository.deleteById(memberId);
	}
}
