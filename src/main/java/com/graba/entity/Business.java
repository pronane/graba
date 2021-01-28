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
	
	private Long locationId;
	
	private Long businessCategoryId;
	
	private String name;
	
	private BusinessType type;
	
	private Long ownerId;
	
	private String ownerName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Long getBusinessCategoryId() {
		return businessCategoryId;
	}

	public void setBusinessCategoryId(Long businessCategoryId) {
		this.businessCategoryId = businessCategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BusinessType getType() {
		return type;
	}

	public void setType(BusinessType type) {
		this.type = type;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	
}
