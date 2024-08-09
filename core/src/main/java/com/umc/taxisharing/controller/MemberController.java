package com.umc.taxisharing.controller;

import com.umc.taxisharing.dto.MemberRequestDTO;
import com.umc.taxisharing.dto.MemberResponse;
import com.umc.taxisharing.service.MemberService;
import com.umc.common.error.exception.CustomException;
import com.umc.common.error.code.CommonErrorCode;
import com.umc.common.response.JsendCommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/members")
public class MemberController {

	@Autowired
	private MemberService memberService;

	/**
	 * 회원 생성 API
	 * @param memberRequestDTO 회원 정보 DTO
	 * @return 생성된 회원 정보
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public JsendCommonResponse<MemberResponse.MemberResponseDTO> createMember(
		@Valid @RequestBody MemberRequestDTO memberRequestDTO) {
		MemberResponse.MemberResponseDTO responseDTO = memberService.createMember(memberRequestDTO);
		return JsendCommonResponse.success(responseDTO);
	}

	/**
	 * 회원 조회 API
	 * @param memberId 회원 ID
	 * @return 조회된 회원 정보
	 */
	@GetMapping("/{memberId}")
	public JsendCommonResponse<MemberResponse.MemberResponseDTO> getMember(
		@PathVariable Long memberId) {
		MemberResponse.MemberResponseDTO responseDTO = memberService.getMember(memberId);
		return JsendCommonResponse.success(responseDTO);
	}

	/**
	 * 회원 수정 API
	 * @param memberId 회원 ID
	 * @param memberRequestDTO 수정할 회원 정보 DTO
	 * @return 수정된 회원 정보
	 */
	@PutMapping("/{memberId}")
	public JsendCommonResponse<MemberResponse.MemberResponseDTO> updateMember(
		@PathVariable Long memberId,
		@Valid @RequestBody MemberRequestDTO memberRequestDTO) {
		MemberResponse.MemberResponseDTO responseDTO = memberService.updateMember(memberId, memberRequestDTO);
		return JsendCommonResponse.success(responseDTO);
	}

	/**
	 * 회원 삭제 API
	 * @param memberId 회원 ID
	 */
	@DeleteMapping("/{memberId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMember(@PathVariable Long memberId) {
		memberService.deleteMember(memberId);
	}

	/**
	 * 예외 처리 (Global Exception Handler에서 처리할 경우)
	 */
	@ExceptionHandler(CustomException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public JsendCommonResponse<String> handleCustomException(CustomException ex) {
		return JsendCommonResponse.error(ex.getErrorCode().getMessage());
	}
}
