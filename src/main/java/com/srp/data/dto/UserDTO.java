package com.srp.data.dto;

import lombok.Data;

@Data
public class UserDTO {

	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String emailId;
	private String role;
	private boolean isActive;

}
