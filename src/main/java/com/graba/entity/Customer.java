package com.graba.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name="Customer")
public class Customer extends User{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6853144086685821272L;


	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private String phoneNumber;
	
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public void setActive(Long id) {
		if(super.getId() == id)
		this.active = true;
	}


	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public String getVerificationCode() {
		return verificationCode;
	}
	
	public void setAuthProvider(String authProvider) {
		this.authProvider = authProvider;
	}

	private String postalCode;
	
	
	private Date createdTime;
	
	private Boolean active;
	
	private String verificationCode;
	
	private String authProvider;
	
	private String resetPasswordToken;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getAuthProvider() {
		return authProvider;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getConfirmationToken() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
