package com.umc.taxisharing.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.umc.taxisharing.domain.TaxiSharingRoom;
import com.umc.taxisharing.service.TaxiSharingRoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TaxiSharingRoomController {

	private final TaxiSharingRoomService taxiSharingRoomService;

	// 방 정보 저장
	@PostMapping
	public ResponseEntity<TaxiSharingRoom> createRoom(@RequestBody TaxiSharingRoom taxiSharingRoom) {
		TaxiSharingRoom savedRoom = taxiSharingRoomService.createRoom(taxiSharingRoom);
		return ResponseEntity.ok(savedRoom);
	}

	// 모든 방 정보 조회
	@GetMapping
	public ResponseEntity<List<TaxiSharingRoom>> getAllRooms() {
		List<TaxiSharingRoom> rooms = taxiSharingRoomService.getAllRooms();
		return ResponseEntity.ok(rooms);
	}

	// 특정 방 정보 조회
	@GetMapping("/{taxiRoomId}")
	public ResponseEntity<TaxiSharingRoom> getRoomById(@PathVariable String taxiRoomId) {
		Optional<TaxiSharingRoom> room = taxiSharingRoomService.getRoomById(taxiRoomId);
		return room.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}
}