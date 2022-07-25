package com.contactapi.serviceimlp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.contactapi.constant.Constant;
import com.contactapi.entity.Contacts;
import com.contactapi.mapper.CustomModelMapper;
import com.contactapi.model.ContactsModel;
import com.contactapi.model.ResponseDTO;
import com.contactapi.repository.ContactRepository;
import com.contactapi.service.ContactService;
import com.contactapi.util.CommonUtil;
import com.contactapi.util.FileUpload;
import com.contactapi.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private CustomModelMapper customModelMapper;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private FileUpload fileUpload;

	@Override
	public Contacts saveOrUpdateRecord(Contacts contacts) {
		// TODO Auto-generated method stub
		return contactRepository.save(contacts);
	}

	@Override
	public boolean deleteRecord(Long id) {
		// TODO Auto-generated method stub
		Contacts contact = getRecord(id);
		if (contact != null) {
			contactRepository.delete(contact);
			return true;
		}
		return false;
	}

	@Override
	public Contacts getRecord(Long id) {
		// TODO Auto-generated method stub
		return contactRepository.findById(id).orElse(null);
	}

	public ResponseDTO<?> saveContactDetails(ContactsModel contact) throws DataIntegrityViolationException {
		// Contacts existContact = contactRepository.findByPhone(contact.getPhone());
		fileUpload.initDirectory(null);
		Contacts contactsObj;
		if (contact.getId() == null || contact.getId().toString().isEmpty()) {
			// new record
			contactsObj = new Contacts();
		} else {
			// update record
			contactsObj = getRecord(contact.getId());
		}
		contactsObj = customModelMapper.convertDtoToEntity(contact, Contacts.class);

		if (contact.getProfilePicture() != null) {
			String fileName = CommonUtil.getAttachmentName(contact.getPhone(), null);
			fileUpload.save(contact.getProfilePicture(), fileName);
			contactsObj.setProfilePictureName(fileName);
		}

		contactsObj = saveOrUpdateRecord(contactsObj);
		contact = customModelMapper.convertEntityToDto(contactsObj, ContactsModel.class);
		if (contactsObj != null) {
			return ResponseUtil.generateResponseDto(contact);
		}
		return ResponseUtil.generateResponseDto(new ObjectMapper(), Constant.SERVER_ERROR_MESSAGE);
	}

	public ResponseDTO<?> deleteContact(Long id) {
		if (deleteRecord(id)) {
			return ResponseUtil.generateResponseDto(new ObjectMapper());
		}else {
			return ResponseUtil.generateResponseDto(new ObjectMapper(), Constant.MESSAGE_NO_RECORD_FOUND);			
		}
	}

	public ResponseDTO<?> getContactList() {
		List<Contacts> contactList = contactRepository.findAll();
		if (contactList == null || contactList.isEmpty()) {
			return ResponseUtil.generateResponseDto(new ObjectMapper(), Constant.MESSAGE_NO_RECORD_FOUND);
		}
		List<ContactsModel> contactDtoList = customModelMapper.mapAllToDtoList(contactList, ContactsModel.class);
		return ResponseUtil.generateResponseDto(contactDtoList);
	}

}
