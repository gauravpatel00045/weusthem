package com.contactapi.exceptionhandler;

import java.util.function.Supplier;

import com.contactapi.constant.Constant;
import com.contactapi.enums.ResponseStatusEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseException extends RuntimeException implements Supplier<ResponseException> {

	private static final long serialVersionUID = 5886850345614790133L;
	private final String message;
	private final Exception exception;
	private final ResponseStatusEnum status;

	public ResponseException(Exception exception) {
		this.exception = exception;
		this.message = Constant.SERVER_ERROR_MESSAGE;
		this.status = ResponseStatusEnum.SERVER_ERROR;
	}

	public ResponseException(Exception exception, String message, ResponseStatusEnum status) {
		this.message = message;
		this.status = status;
		this.exception = exception;
	}

	@Override
	public ResponseException get() {
		return null;
	}

}