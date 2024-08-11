package com.umc.taxisharing.dto;

import java.time.ZonedDateTime;

import com.umc.taxisharing.domain.Money;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaxiSharingRoomRequestDTO {
	private String roomName;
	private String departurePoint;
	private String arrivalPoint;
	private ZonedDateTime departureTime;
	private Money estimateFare;
}