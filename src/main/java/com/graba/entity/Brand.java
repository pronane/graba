package com.graba.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BRAND")
public class Brand {

	@Id
	@GeneratedValue
	private Long id;

}
