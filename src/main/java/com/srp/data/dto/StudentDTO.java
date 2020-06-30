package com.srp.data.dto;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonAutoDetect
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class StudentDTO {
	
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String emailId;
	private Date dateOfBirth;
	private Long age;
	private Long feeReceiptNumber;
	private String enrollmentNumber;
	private Long yearId;
	private Long collegeYear;
	private String motherName;
	private String fatherName;
	private Long whatsappNumber;
	private Long alternatePhoneNumber;
	private Long guardianPhoneNumber;
	private String gender;
	private String category;
	private Long streamId;
	private List<Long> subjectId;
	private String streamName;
	private List<String> subjectName;
	private String groupDivision;
	private String studentCode;
	private Long studentOTP;
	private Date createdDate;
	private Date modifiedDate;
	private String modifiedBy;
	private String role;
	private boolean verified;
}
