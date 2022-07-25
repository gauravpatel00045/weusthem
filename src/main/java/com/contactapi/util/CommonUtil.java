package com.contactapi.util;

import com.contactapi.constant.Constant;

public class CommonUtil {

	public static String getErrorMessage(String errorDetails) {
		String errorMessage = null;
		if (errorDetails == null || errorDetails.isEmpty()) {
			errorMessage = Constant.MESSAGE_NO_RECORD_FOUND;
		}
		// This get field Error name generating form Database
		String fieldName = errorDetails.substring(errorDetails.indexOf("(") + 1, errorDetails.indexOf(")"));
		fieldName = fieldName.replace("_", " ");
		fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		errorMessage = fieldName + Constant.ALREADY_EXIST;
		return errorMessage;
	}
	
	public static String getAttachmentName(String prefix, String attachmentName) {
		if (prefix == null || prefix.isEmpty()) {
			if (attachmentName != null && !attachmentName.isEmpty()) {
				return attachmentName;
			}
		}

		if (attachmentName == null || attachmentName.isEmpty()) {
			return prefix;
		}
		return new StringBuilder().append(prefix).append("_").append(attachmentName).toString();
	}
}
