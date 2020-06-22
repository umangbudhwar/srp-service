package com.srp.data.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Data
@JsonAutoDetect
public class UserDTO {

	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String emailId;
	private String role;
	private boolean isActive;

}
