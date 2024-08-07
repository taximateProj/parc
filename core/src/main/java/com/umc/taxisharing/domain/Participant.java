package com.umc.taxisharing.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "participant")
public class Participant {

	@Id
	private Long participantId;

	private Long memberId;

	private String taxiRoomId;
}