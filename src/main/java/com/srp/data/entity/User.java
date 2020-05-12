package com.srp.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private String role;

	@Column(name = "is_active")
	private boolean isActive;

	@Id
	@Column(name = "user_name")
	private String userName;

}
