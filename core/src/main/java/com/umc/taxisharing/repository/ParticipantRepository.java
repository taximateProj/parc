package com.umc.taxisharing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umc.taxisharing.domain.Participant;

public interface ParticipantRepository extends MongoRepository<Participant, Long> {
}
