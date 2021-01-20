package com.graba.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="cart_item")
public class CartItem {

	@Id
	@GeneratedValue
	private Long id;
	
	 @JoinTable(name = "customer", 
			 joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
			 inverseJoinColumns = @JoinColumn(name = "cart_item_id", referencedColumnName = "id"))
	@ManyToOne(targetEntity = Customer.class)
	private Customer customer;
	
	 @JoinTable(name = "product", 
			 joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
			 inverseJoinColumns = @JoinColumn(name = "cart_item_id", referencedColumnName = "id"))
	@ManyToOne(targetEntity = Product.class)
	private Product product;
	
	private Integer quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Transient
	public float getSubtotal() {
		return this.product.getPrice() * quantity;
	}
	
}
