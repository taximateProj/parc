package com.umc.taxisharing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantDTO {

	private Long participantId;
	private Long memberId;
	private String taxiRoomId;
}
