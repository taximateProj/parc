package com.umc.taxisharing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.umc.taxisharing.domain.TaxiSharingRoom;
import com.umc.taxisharing.dto.TaxiSharingRoomInfoResponse;
import com.umc.taxisharing.repository.TaxiSharingRoomRepository;
import com.umc.taxisharing.utils.JWTUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaxiSharingRoomService {

	private final TaxiSharingRoomRepository taxiSharingRoomRepository;

	public List<TaxiSharingRoomInfoResponse> getTaxiSharingRooms(String jwtToken, int size, String lastRoomId) {
		String userId = JWTUtils.getUserIdFromToken(jwtToken);

		PageRequest pageRequest = PageRequest.of(0, size + 1);

		List<TaxiSharingRoom> rooms;
		if (lastRoomId == null) {
			rooms = taxiSharingRoomRepository.findAllByOrderByIdDesc(pageRequest);
		} else {
			rooms = taxiSharingRoomRepository.findAllByIdLessThanOrderByIdDesc(lastRoomId, pageRequest);
		}

		// If rooms size is greater than size, it means there are more rooms to load
		boolean hasMore = rooms.size() > size;
		if (hasMore) {
			rooms = rooms.subList(0, size);
		}

		return rooms.stream()
			.map(this::toTaxiSharingRoomInfoResponse)
			.collect(Collectors.toList());
	}

	private TaxiSharingRoomInfoResponse toTaxiSharingRoomInfoResponse(TaxiSharingRoom room) {
		return TaxiSharingRoomInfoResponse.builder()
			.roomName(room.getRoomName())
			.departurePoint(room.getDeparturePoint())
			.arrivePoint(room.getArrivePoint())
			.departureTime(room.getDepartureTime())
			.estimatedFare(room.getEstimatedFare())
			.currentMembers(room.getParticipants() != null ? room.getParticipants().size() : 0)
			.totalMembers(4)  // Replace with actual logic if needed
			.isFull(room.getParticipants() != null ? room.getParticipants().size() >= 4 : false)
			.build();
	}
}