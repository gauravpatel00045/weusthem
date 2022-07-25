package com.contactapi.exceptionhandler;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.dao.DataIntegrityViolationException;
import org.hibernate.exception.ConstraintViolationException;

import com.contactapi.constant.Constant;
import com.contactapi.enums.ResponseStatusEnum;
import com.contactapi.model.ResponseDTO;
import com.contactapi.util.CommonUtil;
import com.contactapi.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;


@ControllerAdvice
public class GlobalExceptionHandler {

	// Handle model annotation validation e.g @Notnull, @Notblank
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO<?>> resourceNotFoundHandling(MethodArgumentNotValidException exception) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "validation Error",
				exception.getBindingResult().getFieldError().getDefaultMessage());
		return ResponseEntity.ok(ResponseUtil.generateResponseDto(new ObjectMapper(), errorDetails.getDetails(),
				ResponseStatusEnum.BAD_REQUEST));
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ResponseDTO<?>> dataIntegrityViolationException(DataIntegrityViolationException exception) {
		ConstraintViolationException cve = (ConstraintViolationException) exception.getCause();
		String failDetails = cve.getSQLException().getMessage();
		return ResponseEntity.ok(ResponseUtil.generateResponseDto(new ObjectMapper(),
				CommonUtil.getErrorMessage(failDetails), ResponseStatusEnum.BAD_REQUEST));
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<ResponseDTO<?>> handleMaxSizeException(MaxUploadSizeExceededException exception) {
		return ResponseEntity.ok(ResponseUtil.generateResponseDto(new ObjectMapper(), Constant.MESSAGE_FILE_SIZE_EXCEED,
				ResponseStatusEnum.BAD_REQUEST));
	}

}
