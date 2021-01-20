package com.graba.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COUNTRY")
public class Country {

	@Id
	@GeneratedValue
	private Long id;
	
	private String byNameAsc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getByNameAsc() {
		return byNameAsc;
	}

	public void setByNameAsc(String byNameAsc) {
		this.byNameAsc = byNameAsc;
	}
}
