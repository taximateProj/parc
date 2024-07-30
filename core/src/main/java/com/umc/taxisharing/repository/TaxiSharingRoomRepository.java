package com.umc.taxisharing.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.umc.taxisharing.domain.TaxiSharingRoom;

public interface TaxiSharingRoomRepository extends MongoRepository<TaxiSharingRoom, String> {

	@Query(sort = "{ 'id': -1 }")
	List<TaxiSharingRoom> findAllByOrderByIdDesc(PageRequest pageRequest);

	@Query("{ 'id': { $lt: ?0 } }")
	List<TaxiSharingRoom> findAllByIdLessThanOrderByIdDesc(String lastRoomId, PageRequest pageRequest);
}