package com.umc.taxisharing.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.umc.taxisharing.domain.Money;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TaxiSharingRoomInfoResponse {
	private String roomName;
	private String departurePoint;
	private String arrivePoint;
	private ZonedDateTime departureTime;
	private Money estimatedFare;
	private int currentMembers;
	private int totalMembers;
	private boolean isFull;
}