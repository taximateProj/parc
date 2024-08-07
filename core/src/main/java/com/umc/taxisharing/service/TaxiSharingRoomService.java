package com.umc.taxisharing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.umc.taxisharing.domain.TaxiSharingRoom;
import com.umc.taxisharing.repository.ParticipantRepository;
import com.umc.taxisharing.repository.TaxiSharingRoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaxiSharingRoomService {

	private final TaxiSharingRoomRepository taxiSharingRoomRepository;

	// 방 정보 저장
	public TaxiSharingRoom createRoom(TaxiSharingRoom taxiSharingRoom) {
		return taxiSharingRoomRepository.save(taxiSharingRoom);
	}

	// 모든 방 정보 조회
	public List<TaxiSharingRoom> getAllRooms() {
		return taxiSharingRoomRepository.findAll();
	}

	// 특정 방 정보 조회
	public Optional<TaxiSharingRoom> getRoomById(String taxiRoomId) {
		return taxiSharingRoomRepository.findById(taxiRoomId);
	}

}
