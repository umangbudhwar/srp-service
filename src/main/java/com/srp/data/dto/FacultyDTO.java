package com.srp.data.dto;

import lombok.Data;

@Data
public class FacultyDTO {

	//private Long Faculty_id;
	private String firstName;
	private String lastName;
	private String emailId;
	private String password;
	private String userName;
	private Long contactNumber;
	private Integer adminOTP;
}
