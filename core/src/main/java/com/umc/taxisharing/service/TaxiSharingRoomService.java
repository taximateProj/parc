package com.umc.taxisharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umc.taxisharing.converter.TaxiSharingRoomConverter;
import com.umc.taxisharing.domain.TaxiSharingRoom;
import com.umc.taxisharing.dto.CreateTaxiSharingRoomRequestDTO;
import com.umc.taxisharing.dto.TaxiSharingRoomResponseDTO;
import com.umc.taxisharing.repository.TaxiSharingRoomRepository;
import com.umc.common.error.code.CommonErrorCode;
import com.umc.common.error.exception.CustomException;

@Service
public class TaxiSharingRoomService {

	// 의존성 주입: TaxiSharingRoomRepository는 MongoDB와의 상호작용을 담당
	@Autowired
	private TaxiSharingRoomRepository taxiSharingRoomRepository;

	// 의존성 주입: TaxiSharingRoomConverter는 DTO와 Entity 간의 변환을 담당
	@Autowired
	private TaxiSharingRoomConverter taxiSharingRoomConverter;

	/**
	 * 새로운 택시방을 생성합니다.
	 * @param requestDTO 생성할 택시방의 정보가 담긴 DTO
	 * @return 생성된 택시방의 정보를 담은 DTO
	 */
	@Transactional
	public TaxiSharingRoomResponseDTO createTaxiSharingRoom(CreateTaxiSharingRoomRequestDTO requestDTO) {
		// DTO를 Entity로 변환
		TaxiSharingRoom taxiSharingRoom = taxiSharingRoomConverter.toEntity(requestDTO);

		// MongoDB에 택시방 저장
		TaxiSharingRoom savedRoom = taxiSharingRoomRepository.save(taxiSharingRoom);

		// 저장된 택시방을 DTO로 변환하여 반환
		return taxiSharingRoomConverter.toDTO(savedRoom);
	}

	/**
	 * 택시방 정보를 조회합니다.
	 * @param taxiRoomId 조회할 택시방의 ID
	 * @return 조회된 택시방의 정보를 담은 DTO
	 * @throws CustomException 택시방이 존재하지 않을 경우 발생
	 */
	@Transactional(readOnly = true)
	public TaxiSharingRoomResponseDTO getTaxiSharingRoom(String taxiRoomId) {
		// ID로 택시방 조회, 존재하지 않으면 CustomException을 던짐
		TaxiSharingRoom taxiSharingRoom = taxiSharingRoomRepository.findById(taxiRoomId)
			.orElseThrow(() -> new CustomException(CommonErrorCode.NOT_FOUND));

		// 조회된 택시방을 DTO로 변환하여 반환
		return taxiSharingRoomConverter.toDTO(taxiSharingRoom);
	}
}
