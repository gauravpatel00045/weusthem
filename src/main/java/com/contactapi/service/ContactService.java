package com.contactapi.service;

import com.contactapi.entity.Contacts;

public interface ContactService {
	
	public Contacts saveOrUpdateRecord(Contacts contacts); 

	public boolean deleteRecord(Long id);
	
	public Contacts getRecord(Long id);
	
}
