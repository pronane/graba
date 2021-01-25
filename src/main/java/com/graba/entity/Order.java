package com.graba.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="customer_order")
public class Order {
	
	@GeneratedValue
	@Id
	private Long id;
	
	@Transient
	private List<Long> orderItemId;
	
	private Float total;
	
	private Date orderDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Long> getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(List<Long> orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
}
