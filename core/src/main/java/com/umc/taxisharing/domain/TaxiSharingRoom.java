package com.umc.taxisharing.domain;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.umc.taxisharing.mapping.Participant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document(collection = "taxiSharingRooms")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TaxiSharingRoom {

	@Id
	private String id;

	private String roomName;

	private String departurePoint;

	private String arrivePoint;

	private ZonedDateTime departureTime;

	private Money estimatedFare;

	private List<Participant> participants;
}