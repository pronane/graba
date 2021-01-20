package com.graba.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
public class UserRole {
	
	@Id
	@GeneratedValue
	private Long id;

}
