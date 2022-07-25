package com.contactapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contactapi.constant.ApiPathConstant;
import com.contactapi.enums.ResponseStatusEnum;
import com.contactapi.exceptionhandler.ResponseException;
import com.contactapi.model.ContactsModel;
import com.contactapi.model.ResponseDTO;
import com.contactapi.serviceimlp.ContactServiceImpl;
import com.contactapi.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ContactController {

	@Autowired
	private ContactServiceImpl contactServiceImpl;

	/**
	 * API to save or update new record.
	 * 
	 */
	@PostMapping(value = ApiPathConstant.API_SAVE_CONTACT, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ResponseDTO<?>> saveContact(@Valid @ModelAttribute ContactsModel contact) {
		try {
			return ResponseEntity.ok(contactServiceImpl.saveContactDetails(contact));
		} catch (ResponseException e) {
			return ResponseEntity.ok(ResponseUtil.generateResponseDto(new ObjectMapper(), e.getMessage(),
					ResponseStatusEnum.SERVER_ERROR));
		}
	}

	/**
	 * API to get the list.
	 * 
	 */
	@GetMapping(value = ApiPathConstant.API_GET_CONTACT_LIST)
	public ResponseEntity<ResponseDTO<?>> getContactList() {
		try {
			return ResponseEntity.ok(contactServiceImpl.getContactList());
		} catch (ResponseException e) {
			return ResponseEntity.ok(ResponseUtil.generateResponseDto(new ObjectMapper(), e.getMessage(),
					ResponseStatusEnum.SERVER_ERROR));
		}
	}

	/**
	 * API to perform the delete operation.
	 * 
	 */
	@DeleteMapping(value = ApiPathConstant.API_DELETE_CONTACT)
	public ResponseEntity<ResponseDTO<?>> deleteStatus(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(contactServiceImpl.deleteContact(id));
		} catch (ResponseException e) {
			return ResponseEntity.ok(ResponseUtil.generateResponseDto(new ObjectMapper(), e.getMessage(),
					ResponseStatusEnum.SERVER_ERROR));
		}
	}

}
