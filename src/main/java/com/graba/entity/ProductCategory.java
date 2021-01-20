package com.graba.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Product_Category")
public class ProductCategory {

	@Id
	@GeneratedValue
	private Long id;
}
