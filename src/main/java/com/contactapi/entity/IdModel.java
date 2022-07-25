package com.contactapi.entity;

import javax.persistence.*;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class IdModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

}
