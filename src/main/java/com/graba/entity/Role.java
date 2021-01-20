package com.graba.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Role")
public class Role {

	@Id
	@GeneratedValue
	private Long id;
	
	public String getName() {
		// TODO Auto-generated method stub
		return "Admin";
	}
	
	
	 @JoinTable(name = "user", 
			 joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			 inverseJoinColumns = @JoinColumn(name = "user_role_id", referencedColumnName = "id"))
	@OneToMany(targetEntity = UserRole.class)
	private Set<UserRole> userRoles = new HashSet<UserRole>();

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}
