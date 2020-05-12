package com.srp.data.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class StudentDTO {
	
	private String userName;
	private String password;
	private String first_name;
	private String last_name;
	private String email_id;
	private Date date_of_birth;
	private Integer age;
	private Long fee_receipt_number;
	private String enrollment_number;
	// private Integer year_id;
	private Integer college_year;
	private String mother_name;
	private String father_name;
	private Long whatsapp_number;
	private Long alternate_phone_number;
	private Long guardian_phone_number;
	private String gender;
	private String category;
	private String stream_id;
	private String[] subject_id;
	private String group_division;
	private String student_code;
	
}
