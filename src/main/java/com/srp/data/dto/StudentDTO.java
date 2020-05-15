package com.srp.data.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class StudentDTO {
	
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String emailId;
	private Date DOB;
	private Integer age;
	private Long feeReceiptNumber;
	private String enrollmentNumber;
	private Integer yearId;
	private Integer collegeYear;
	private String motherName;
	private String fatherName;
	private Long whatsappNumber;
	private Long alternatePhoneNumber;
	private Long guardianPhoneNumber;
	private String gender;
	private String category;
	private String streamId;
	private Integer[] subjectId;
	private String groupDivision;
	private String studentCode;
	private Long studentOTP;
}
