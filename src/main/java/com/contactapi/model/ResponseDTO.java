package com.contactapi.model;

import java.io.Serializable;

import com.contactapi.constant.Constant;
import com.contactapi.enums.ResponseStatusEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDTO<T> implements Serializable {

	private static final long serialVersionUID = -7791217884470823543L;
	private String message;
	private Integer status;
	private Boolean isError;
	private transient T body;

	public ResponseDTO(T object) {
		this.message = Constant.SUCCESS;
		this.isError = Boolean.TRUE;
		this.status = ResponseStatusEnum.OK.getValue();
		this.body = object;
		isError = Boolean.FALSE;
	}

	public ResponseDTO(T object, String message, ResponseStatusEnum status) {
		this.message = message;
		this.isError = Boolean.TRUE;
		this.status = status.getValue();
		this.body = object;
	}

	public ResponseDTO<T> setSuccessMessage(String message) {
		this.message = message;
		this.isError = Boolean.FALSE;
		this.status = ResponseStatusEnum.OK.getValue();
		return this;
	}

	public ResponseDTO<T> setErrorMessage(String message, ResponseStatusEnum status) {
		this.message = message;
		this.isError = Boolean.TRUE;
		this.status = status.getValue();
		return this;
	}

}
