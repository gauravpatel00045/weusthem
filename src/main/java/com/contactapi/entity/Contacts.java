package com.contactapi.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contacts")
public class Contacts extends IdModel {

	@Column(name = "first_name", columnDefinition = "VARCHAR(45)", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", columnDefinition = "VARCHAR(45)", nullable = false)
	private String lastName;
	
	@Column(name = "email", columnDefinition = "VARCHAR(255)", nullable = false)
	private String email;
	
	@Column(name = "phone_number", columnDefinition = "VARCHAR(17)", nullable = false, unique = true)
	private String phone;
	
	@Column(name = "profile_picture", columnDefinition = "VARCHAR(255)", nullable = true)
	private String profilePictureName;

}
