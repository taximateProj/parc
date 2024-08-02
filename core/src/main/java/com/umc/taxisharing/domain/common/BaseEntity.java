package com.umc.taxisharing.domain.common;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

public abstract class BaseEntity {

	@Field
	@CreatedDate
	private LocalDateTime createdAt;

	@Field
	@LastModifiedDate
	private LocalDateTime updatedAt;

	// Getters 추가
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}