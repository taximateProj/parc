package com.umc.taxisharing.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.umc.taxisharing.domain.TaxiSharingRoom;

public interface TaxiSharingRoomRepository extends MongoRepository<TaxiSharingRoom, String> {

	List<TaxiSharingRoom> findAllByOrderByIdDesc(PageRequest pageRequest);

	List<TaxiSharingRoom> findAllByIdLessThanOrderByIdDesc(String lastRoomId, PageRequest pageRequest);
}