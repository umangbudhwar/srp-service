package com.srp.data.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Data
@JsonAutoDetect
public class FacultyDTO {

	//private Long Faculty_id;
	private String firstName;
	private String lastName;
	private String emailId;
	private String password;
	private String userName;
	private Long contactNumber;
	private Long adminOTP;
	private Long streamId;
}
