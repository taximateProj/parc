package com.umc.taxisharing.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.umc.taxisharing.dto.TaxiSharingRoomInfoResponse;
import com.umc.taxisharing.service.TaxiSharingRoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TaxiSharingRoomController {

	private final TaxiSharingRoomService taxiSharingRoomService;

	@GetMapping("/taxi-sharing-rooms")
	public ResponseEntity<List<TaxiSharingRoomInfoResponse>> getTaxiSharingRooms(
		@RequestHeader("Authorization") String jwtToken,
		@RequestParam(value = "size", defaultValue = "10") int size,
		@RequestParam(value = "lastRoomId", required = false) String lastRoomId) {

		List<TaxiSharingRoomInfoResponse> rooms = taxiSharingRoomService.getTaxiSharingRooms(jwtToken, size, lastRoomId);

		return ResponseEntity.ok(rooms);
	}
}