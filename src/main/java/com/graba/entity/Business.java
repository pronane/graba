package com.graba.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.graba.enums.BusinessType;

@Entity
public class Business {

	@Id
	@GeneratedValue
	private Long id;
	
	private Long categoryId;
	
	private String name;
	
	private BusinessType type;
	
	private Long ownerId;
	
}
