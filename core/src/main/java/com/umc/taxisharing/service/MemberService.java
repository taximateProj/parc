package com.umc.taxisharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import com.umc.taxisharing.domain.BankAccount;
import com.umc.taxisharing.domain.Gender;
import com.umc.taxisharing.domain.Member;
import com.umc.taxisharing.dto.BankAccountDTO;
import com.umc.taxisharing.dto.MemberRequestDTO;
import com.umc.taxisharing.dto.MemberResponse;
import com.umc.taxisharing.repository.MemberRepository;
import com.umc.common.error.exception.CustomException;
import com.umc.common.error.code.CommonErrorCode;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	/**
	 * 회원 생성 메서드
	 * @param memberRequestDTO 생성할 회원 정보가 담긴 DTO
	 * @return 생성된 회원 정보가 담긴 DTO
	 */
	@Transactional
	public MemberResponse.MemberResponseDTO createMember(MemberRequestDTO memberRequestDTO) {
		Member member = new Member();
		member.setName(memberRequestDTO.getName());
		member.setNickName(memberRequestDTO.getNickName());
		member.setUniversity(memberRequestDTO.getUniversity());
		member.setPhoneNumber(memberRequestDTO.getPhoneNumber());
		member.setGender(memberRequestDTO.getGender()); // Gender enum 설정

		// BankAccountDTO 리스트를 BankAccount 엔티티 리스트로 변환
		List<BankAccount> bankAccounts = memberRequestDTO.getBankAccounts().stream()
			.map(dto -> {
				BankAccount bankAccount = new BankAccount();
				bankAccount.setBank(dto.getBank());
				bankAccount.setAccountNumber(dto.getAccountNumber());
				bankAccount.setMember(member); // BankAccount와 Member 관계 설정
				return bankAccount;
			})
			.collect(Collectors.toList());

		member.setBankAccounts(bankAccounts); // Member 엔티티에 BankAccount 설정

		Member savedMember = memberRepository.save(member); // 회원 저장

		return MemberResponse.MemberResponseDTO.builder()
			.memberId(savedMember.getMemberId())
			.name(savedMember.getName())
			.nickName(savedMember.getNickName())
			.gender(savedMember.getGender()) // Gender enum 설정
			.university(savedMember.getUniversity())
			.phoneNumber(savedMember.getPhoneNumber())
			.bankAccounts(savedMember.getBankAccounts().stream()
				.map(this::toBankAccountDTO)
				.collect(Collectors.toList())) // BankAccount 엔티티를 DTO로 변환
			.build();
	}

	/**
	 * 회원 조회 메서드
	 * @param memberId 조회할 회원의 ID
	 * @return 조회된 회원 정보가 담긴 DTO
	 */
	@Transactional(readOnly = true)
	public MemberResponse.MemberResponseDTO getMember(Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new CustomException(CommonErrorCode.MEMBER_NOT_FOUND)); // 회원이 존재하지 않으면 예외 발생

		return MemberResponse.MemberResponseDTO.builder()
			.memberId(member.getMemberId())
			.name(member.getName())
			.nickName(member.getNickName())
			.gender(member.getGender()) // Gender enum 설정
			.university(member.getUniversity())
			.phoneNumber(member.getPhoneNumber())
			.bankAccounts(member.getBankAccounts().stream()
				.map(this::toBankAccountDTO)
				.collect(Collectors.toList())) // BankAccount 엔티티를 DTO로 변환
			.build();
	}

	/**
	 * 회원 수정 메서드
	 * @param memberId 수정할 회원의 ID
	 * @param memberRequestDTO 수정할 회원 정보가 담긴 DTO
	 * @return 수정된 회원 정보가 담긴 DTO
	 */
	@Transactional
	public MemberResponse.MemberResponseDTO updateMember(Long memberId, MemberRequestDTO memberRequestDTO) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new CustomException(CommonErrorCode.MEMBER_NOT_FOUND)); // 회원이 존재하지 않으면 예외 발생

		member.setName(memberRequestDTO.getName());
		member.setNickName(memberRequestDTO.getNickName());
		member.setUniversity(memberRequestDTO.getUniversity());
		member.setPhoneNumber(memberRequestDTO.getPhoneNumber());
		member.setGender(memberRequestDTO.getGender()); // Gender enum 설정

		// BankAccountDTO 리스트를 BankAccount 엔티티 리스트로 변환
		List<BankAccount> bankAccounts = memberRequestDTO.getBankAccounts().stream()
			.map(dto -> {
				BankAccount bankAccount = new BankAccount();
				bankAccount.setBank(dto.getBank());
				bankAccount.setAccountNumber(dto.getAccountNumber());
				bankAccount.setMember(member); // BankAccount와 Member 관계 설정
				return bankAccount;
			})
			.collect(Collectors.toList());

		member.setBankAccounts(bankAccounts); // Member 엔티티에 BankAccount 설정

		Member updatedMember = memberRepository.save(member); // 회원 수정 저장

		return MemberResponse.MemberResponseDTO.builder()
			.memberId(updatedMember.getMemberId())
			.name(updatedMember.getName())
			.nickName(updatedMember.getNickName())
			.gender(updatedMember.getGender()) // Gender enum 설정
			.university(updatedMember.getUniversity())
			.phoneNumber(updatedMember.getPhoneNumber())
			.bankAccounts(updatedMember.getBankAccounts().stream()
				.map(this::toBankAccountDTO)
				.collect(Collectors.toList())) // BankAccount 엔티티를 DTO로 변환
			.build();
	}

	/**
	 * 회원 삭제 메서드
	 * @param memberId 삭제할 회원의 ID
	 */
	@Transactional
	public void deleteMember(Long memberId) {
		if (!memberRepository.existsById(memberId)) {
			throw new CustomException(CommonErrorCode.MEMBER_NOT_FOUND); // 회원이 존재하지 않으면 예외 발생
		}
		memberRepository.deleteById(memberId); // 회원 삭제
	}

	/**
	 * BankAccount 엔티티를 BankAccountDTO로 변환
	 * @param bankAccount 변환할 BankAccount 엔티티
	 * @return 변환된 BankAccountDTO
	 */
	private BankAccountDTO toBankAccountDTO(BankAccount bankAccount) {
		return new BankAccountDTO(
			bankAccount.getId(),
			bankAccount.getBank(),
			bankAccount.getAccountNumber()
		);
	}
}
