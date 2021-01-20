package com.graba.common.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.graba.entity.Product;


@Entity
@Table(name="product_image")
public class ProductImage {

	public ProductImage(String imageName, Product product) {
		// TODO Auto-generated constructor stub
	}


	@Id
	@GeneratedValue
	private Long id;

}
