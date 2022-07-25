package com.contactapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatusEnum {

	OK(200), BAD_REQUEST(400), NOT_FOUND(404), UNAUTH(403), ALREADY_EXISTS(401), SERVER_ERROR(500), NO_CONTENT(204),
	SEND_VERIFICATION_EMAIL(601);

	private final int value;

}