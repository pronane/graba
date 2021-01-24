package com.graba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="cart_item")
public class CartItem {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name="customer_Id")
	private Long customerId;
	
	@Column(name="product_id")
	private Long productId;
	
	private Integer quantity;
	
	@Transient
	private Float subTotal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customer) {
		this.customerId = customer;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long product) {
		this.productId = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Transient
	public Float getSubtotal() {
		//return this.product.getPrice() * quantity;
		return this.subTotal;
	}
	
	public void setSubTotal(Float subTotal) {
		this.subTotal = subTotal;
	}
	
}
