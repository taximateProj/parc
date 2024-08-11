package com.umc.taxisharing.repository;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.umc.taxisharing.domain.TaxiSharingRoom;

public interface TaxiSharingRoomRepository extends MongoRepository<TaxiSharingRoom, String> {

	List<TaxiSharingRoom> findAllByDepartureTimeBeforeOrderByDepartureTimeDesc(ZonedDateTime departureTime, PageRequest pageRequest);
}