package com.contactapi.util;

import com.contactapi.enums.ResponseStatusEnum;
import com.contactapi.model.ResponseDTO;

public class ResponseUtil {

	private ResponseUtil() {
		throw new IllegalStateException();
	}

	public static ResponseDTO<Object> generateResponseDto(Object object) {
		return new ResponseDTO<Object>(object);
	}

	public static ResponseDTO<Object> generateResponseDto(Object o, String message) {
		return new ResponseDTO<Object>(o).setSuccessMessage(message);
	}

	public static ResponseDTO<Object> generateResponseDto(Object o, String message, ResponseStatusEnum status) {
		return new ResponseDTO<Object>(o).setErrorMessage(message, status);
	}

}
