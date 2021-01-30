package com.graba.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.graba.enums.BusinessType;

@Entity
public class Business {

	@Id
	@GeneratedValue
	private Long id;
	
	private Long locationId;
	
	private Long categoryId;
	
	private String name;
	
	private BusinessType type;
	
	private String imagePath;
	
	
	/**
	 * @TODO update to be a manager/owner
	 */
	@Transient
	private List<ItemSubCategory> itemSubCategories;
	
	public List<ItemSubCategory> getItemSubCategories() {
		return itemSubCategories;
	}

	public void setItemSubCategories(List<ItemSubCategory> itemSubCategories) {
		this.itemSubCategories = itemSubCategories;
	}

	/**
	 * @TODO update to be a manager/owner
	 */
	@Transient
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}	
}
