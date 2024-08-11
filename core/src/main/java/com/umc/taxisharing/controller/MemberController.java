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
	 * @param memberRequestDTO 회원 생성에 필요한 정보를 담고 있는 DTO
	 * @return 생성된 회원 정보가 담긴 응답
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) // 생성된 자원을 나타내는 201 Created 상태 코드를 반환
	public JsendCommonResponse<MemberResponse.MemberResponseDTO> createMember(
		@Valid @RequestBody MemberRequestDTO memberRequestDTO) { // 요청 본문에서 DTO를 읽고 유효성 검사를 수행
		MemberResponse.MemberResponseDTO responseDTO = memberService.createMember(memberRequestDTO); // 회원 생성 서비스 호출
		return JsendCommonResponse.success(responseDTO); // 성공 응답 반환
	}

	/**
	 * 회원 조회 API
	 * @param memberId 조회할 회원의 ID
	 * @return 조회된 회원 정보가 담긴 응답
	 */
	@GetMapping("/{memberId}")
	public JsendCommonResponse<MemberResponse.MemberResponseDTO> getMember(
		@PathVariable Long memberId) { // URL 경로에서 회원 ID를 읽음
		MemberResponse.MemberResponseDTO responseDTO = memberService.getMember(memberId); // 회원 조회 서비스 호출
		return JsendCommonResponse.success(responseDTO); // 성공 응답 반환
	}

	/**
	 * 회원 수정 API
	 * @param memberId 수정할 회원의 ID
	 * @param memberRequestDTO 수정할 회원 정보가 담긴 DTO
	 * @return 수정된 회원 정보가 담긴 응답
	 */
	@PutMapping("/{memberId}")
	public JsendCommonResponse<MemberResponse.MemberResponseDTO> updateMember(
		@PathVariable Long memberId,
		@Valid @RequestBody MemberRequestDTO memberRequestDTO) { // 요청 본문에서 DTO를 읽고 유효성 검사를 수행
		MemberResponse.MemberResponseDTO responseDTO = memberService.updateMember(memberId, memberRequestDTO); // 회원 수정 서비스 호출
		return JsendCommonResponse.success(responseDTO); // 성공 응답 반환
	}

	/**
	 * 회원 삭제 API
	 * @param memberId 삭제할 회원의 ID
	 */
	@DeleteMapping("/{memberId}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // 성공적인 삭제를 나타내는 204 No Content 상태 코드를 반환
	public void deleteMember(@PathVariable Long memberId) {
		memberService.deleteMember(memberId); // 회원 삭제 서비스 호출
	}

	/**
	 * 예외 처리 (Global Exception Handler에서 처리할 경우)
	 * @param ex 발생한 사용자 정의 예외
	 * @return 오류 메시지가 담긴 응답
	 */
	@ExceptionHandler(CustomException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 서버 오류를 나타내는 500 Internal Server Error 상태 코드를 반환
	public JsendCommonResponse<String> handleCustomException(CustomException ex) {
		return JsendCommonResponse.error(ex.getErrorCode().getMessage()); // 예외 메시지를 포함한 오류 응답 반환
	}
}
