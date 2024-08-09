package com.umc.taxisharing.domain;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "taxiSharingRoom")
public class TaxiSharingRoom {

	@Id
	private String taxiRoomId;

	private String roomName;

	private String departurePoint;

	private String arrivalPoint;

	private ZonedDateTime departureTime;

	private Money estimateFare;

}