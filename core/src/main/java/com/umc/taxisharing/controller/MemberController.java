package com.umc.taxisharing.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umc.taxisharing.domain.Member;
import com.umc.taxisharing.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	// 특정 멤버 조회
	@GetMapping("/{id}")
	public ResponseEntity<Member> getMemberById(@PathVariable("id") Long memberId) {
		Optional<Member> member = memberService.getMemberById(memberId);
		return member.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	// 모든 멤버 조회
	@GetMapping
	public ResponseEntity<List<Member>> getAllMembers() {
		List<Member> members = memberService.getAllMembers();
		return ResponseEntity.ok(members);
	}

	// 멤버 생성
	@PostMapping
	public ResponseEntity<Member> createMember(@RequestBody Member member) {
		Member createdMember = memberService.createMember(member);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
	}

	// 멤버 정보 업데이트
	@PutMapping("/{id}")
	public ResponseEntity<Member> updateMember(@PathVariable("id") Long memberId, @RequestBody Member memberDetails) {
		Member updatedMember = memberService.updateMember(memberId, memberDetails);
		return ResponseEntity.ok(updatedMember);
	}

	// 멤버 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMember(@PathVariable("id") Long memberId) {
		memberService.deleteMember(memberId);
		return ResponseEntity.noContent().build();
	}
}