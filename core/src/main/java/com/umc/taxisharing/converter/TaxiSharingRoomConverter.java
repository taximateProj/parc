package com.umc.taxisharing.converter;

import org.springframework.stereotype.Component;

import com.umc.taxisharing.domain.TaxiSharingRoom;
import com.umc.taxisharing.dto.CreateTaxiSharingRoomRequestDTO;
import com.umc.taxisharing.dto.TaxiSharingRoomResponseDTO;

@Component
public class TaxiSharingRoomConverter {

	public TaxiSharingRoom toEntity(CreateTaxiSharingRoomRequestDTO dto) {
		TaxiSharingRoom taxiSharingRoom = new TaxiSharingRoom();
		taxiSharingRoom.setRoomName(dto.getRoomName());
		taxiSharingRoom.setDeparturePoint(dto.getDeparturePoint());
		taxiSharingRoom.setArrivalPoint(dto.getArrivalPoint());
		taxiSharingRoom.setDepartureTime(dto.getDepartureTime());
		taxiSharingRoom.setEstimateFare(dto.getEstimateFare());
		return taxiSharingRoom;
	}

	public TaxiSharingRoomResponseDTO toDTO(TaxiSharingRoom entity) {
		TaxiSharingRoomResponseDTO dto = new TaxiSharingRoomResponseDTO();
		dto.setTaxiRoomId(entity.getId());
		dto.setRoomName(entity.getRoomName());
		dto.setDeparturePoint(entity.getDeparturePoint());
		dto.setArrivalPoint(entity.getArrivalPoint());
		dto.setDepartureTime(entity.getDepartureTime());
		dto.setEstimateFare(entity.getEstimateFare());
		return dto;
	}
}