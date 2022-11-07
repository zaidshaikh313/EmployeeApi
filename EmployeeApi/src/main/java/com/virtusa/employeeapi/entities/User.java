package com.virtusa.employeeapi.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String userPassword;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private Set<UserRole> roles=new HashSet<>();
	public void addRole(UserRole role) {
		roles.add(role);
	}
	public void removeRole(UserRole role)
	{
		roles.remove(role);
	}
}
