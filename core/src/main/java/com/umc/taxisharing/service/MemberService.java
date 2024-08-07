package com.umc.taxisharing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.umc.taxisharing.domain.Member;
import com.umc.taxisharing.exception.ResourceNotFoundException;
import com.umc.taxisharing.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	// 특정 멤버를 조회
	public Optional<Member> getMemberById(Long memberId) {
		return memberRepository.findById(memberId);
	}

	// 모든 멤버를 조회
	public List<Member> getAllMembers() {
		return memberRepository.findAll();
	}

	// 새로운 멤버를 생성
	public Member createMember(Member member) {
		return memberRepository.save(member);
	}

	// 멤버 정보를 업데이트
	public Member updateMember(Long memberId, Member memberDetails) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new ResourceNotFoundException("Member", "id", memberId));

		member.setName(memberDetails.getName());
		member.setNickName(memberDetails.getNickName());
		member.setGender(memberDetails.getGender());
		member.setUniversity(memberDetails.getUniversity());
		member.setBankAccounts(memberDetails.getBankAccounts());

		return memberRepository.save(member);
	}

	// 멤버를 삭제
	public void deleteMember(Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new ResourceNotFoundException("Member", "id", memberId));
		memberRepository.delete(member);
	}
}
