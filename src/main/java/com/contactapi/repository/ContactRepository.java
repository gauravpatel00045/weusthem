package com.contactapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contactapi.entity.Contacts;

@Repository
public interface ContactRepository extends JpaRepository<Contacts, Long> {

	public Contacts findByPhone(String phone);

}
