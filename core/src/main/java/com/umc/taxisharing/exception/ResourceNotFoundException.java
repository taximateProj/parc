package com.umc.taxisharing.exception;

import com.umc.common.error.code.CommonErrorCode;
import com.umc.common.error.exception.CustomException;

public class ResourceNotFoundException extends CustomException {
	public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
		super(
			CommonErrorCode.NOT_FOUND,
			String.format("%s not found with %s : '%s'",
				resourceName,
				fieldName,
				fieldValue != null ? fieldValue.toString() : "null"
			)
		);
	}
}
