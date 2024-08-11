package com.umc.taxisharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.umc.taxisharing.dto.CreateTaxiSharingRoomRequestDTO;
import com.umc.taxisharing.dto.TaxiSharingRoomResponseDTO;
import com.umc.taxisharing.service.TaxiSharingRoomService;
import com.umc.common.error.exception.CustomException;
import com.umc.common.response.JsendCommonResponse;

/**
 * 택시 합승 방 관련 요청을 처리하는 컨트롤러입니다.
 */
@RestController
@RequestMapping("/api/taxi-sharing-room")
public class TaxiSharingRoomController {

	@Autowired
	private TaxiSharingRoomService taxiSharingRoomService;

	/**
	 * 택시 방을 생성합니다.
	 *
	 * @param requestDTO 택시 방 생성 요청 데이터
	 * @return 생성된 택시 방 정보를 포함한 성공 응답
	 */
	@PostMapping
	public ResponseEntity<JsendCommonResponse<TaxiSharingRoomResponseDTO>> createTaxiSharingRoom(
		@RequestBody CreateTaxiSharingRoomRequestDTO requestDTO) {
		// 요청 데이터를 기반으로 택시 방을 생성합니다.
		TaxiSharingRoomResponseDTO responseDTO = taxiSharingRoomService.createTaxiSharingRoom(requestDTO);

		// 성공적인 응답을 반환합니다.
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(JsendCommonResponse.success(responseDTO));
	}

	/**
	 * 특정 택시 방의 정보를 조회합니다.
	 *
	 * @param taxiRoomId 조회할 택시 방의 ID
	 * @return 조회된 택시 방 정보를 포함한 성공 응답 또는 에러 응답
	 */
	@GetMapping("/{taxiRoomId}")
	public ResponseEntity<JsendCommonResponse<?>> getTaxiSharingRoom(
		@PathVariable String taxiRoomId) {
		try {
			// 주어진 ID로 택시 방 정보를 조회합니다.
			TaxiSharingRoomResponseDTO responseDTO = taxiSharingRoomService.getTaxiSharingRoom(taxiRoomId);

			// 성공적인 응답을 반환합니다.
			return ResponseEntity.ok(JsendCommonResponse.success(responseDTO));
		} catch (CustomException e) {
			// CustomException 발생 시, 에러 메시지를 포함한 응답을 반환합니다.
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(JsendCommonResponse.error(e.getErrorCode().getMessage()));
		}
	}
}
