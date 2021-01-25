package com.graba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="product_id")
	private Long productId;
	
	private Integer quantity;
	
	private Long orderId;
	
	private Float subTotal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Float getSubtotal() {
		//return this.product.getPrice() * quantity;
		return this.subTotal;
	}
	
	public void setSubTotal(Float subTotal) {
		this.subTotal = subTotal;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
}
