package com.graba.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.entity.enums.ProductType;
import com.graba.common.entity.ProductImage;

@Entity
@Table(name="Product")
public class Product {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	private String name;
	
	private String shortName;
	
	private String alias;
	
	private String shortDescription;
	
	private String fullDescription;
	
	private String mainImage;
	
	private Date createdDate;
	
	private Date updatedDate;
	
	private Boolean active;
	
	private Boolean inStock;
	
	private Float price;
	
	private Float discountPercent;

	private Integer brandId;
	
	private Long categoryId;
	
	private Float length;
	
	private Float width;
	
	private Float height;
	
	private Float weight;
	
	private Float averageRating;
	
	private Integer reviewCount;
	
	private String uri;
	
	private String details;
	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	private ProductType type;
	
	 public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getInStock() {
		return inStock;
	}

	public void setInStock(Boolean inStock) {
		this.inStock = inStock;
	}

	public Float getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Float discountPercent) {
		this.discountPercent = discountPercent;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Float getLength() {
		return length;
	}

	public void setLength(Float length) {
		this.length = length;
	}

	public Float getWidth() {
		return width;
	}

	public void setWidth(Float width) {
		this.width = width;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Float averageRating) {
		this.averageRating = averageRating;
	}

	public Integer getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(Integer reviewCount) {
		this.reviewCount = reviewCount;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public void setImages(Set<ProductImage> images) {
		this.images = images;
	}

	/*
	 * @JoinTable(name = "users_privileges", joinColumns = @JoinColumn(name =
	 * "product_Image", referencedColumnName = "id"), inverseJoinColumns
	 * = @JoinColumn(name = "product_id", referencedColumnName = "id"))
	 * 
	 * @OneToMany(targetEntity=ProductImage.class)
	 */
	@Transient
	private Set<ProductImage> images;

	public Float getPrice() {
		// TODO Auto-generated method stub
		return this.price;
	}
	
	@Transient
	public String getMainImagePath() {
		if(mainImage == null || id == null) 
			return null;
		
		return "/product-images/" + id + "/" + mainImage;
	}
	
	public void addExtraImage(String imageName) {
		this.images.add(new ProductImage(imageName, this));
	}
	
	public Set<ProductImage> getImages() {
		return images;
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public void setCreatedDate(Date date) {
		// TODO Auto-generated method stub
		this.createdDate = date;
	}

	public String getAlias() {
		// TODO Auto-generated method stub
		return this.alias;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public void setAlias(String defaultAlias) {
		// TODO Auto-generated method stub
		this.alias = defaultAlias;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	
}
