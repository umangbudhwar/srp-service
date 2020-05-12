package com.srp.data.entity;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.srp.utility.SrpUtility;

import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student {

	@Id
	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String first_name;

	@Column(name = "last_name")
	private String last_name;

	@Column(name = "email_id")
	private String email_id;

	@Column(name = "date_of_birth")
	private Date date_of_birth;

	@Column(name = "age")
	private Integer age;

	@Column(name = "fee_receipt_number")
	private Long fee_receipt_number;

	@Column(name = "enrollment_number")
	private String enrollment_number;

	@Column(name = "year_id")
	private Integer year_id;

	@Column(name = "college_year")
	private Integer college_year;

	@Column(name = "mother_name")
	private String mother_name;

	@Column(name = "father_name")
	private String father_name;

	@Column(name = "whatsapp_number")
	private Long whatsapp_number;

	@Column(name = "alternate_phone_number")
	private Long alternate_phone_number;

	@Column(name = "guardian_phone_number")
	private Long guardian_phone_number;

	@Column(name = "gender")
	private String gender;

	@Column(name = "category")
	private String category;

	@Column(name = "stream_id")
	private Integer stream_id;

	@Column(name = "subject_id")
	private Integer[] subject_id;

	@Column(name = "group_division")
	private String group_division;

	@Column(name = "student_code")
	private String student_code;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "created_by")
	private Date createdBy;

	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@Column(name = "role")
	private String role;
	
	@PrePersist
	public void prePersist() {
		this.createdDate = new Date(System.currentTimeMillis());
		this.modifiedDate = new Date(System.currentTimeMillis());
		this.modifiedBy = SrpUtility.getCurrentLoggedInUser();
		this.modifiedBy = SrpUtility.getCurrentLoggedInUser();
		this.isActive = true;
		this.role = "ROLE_STUDENT";
		this.year_id = LocalDate.now().getYear();
	}

	@PreUpdate
	public void preUpdate() {
		this.modifiedBy = SrpUtility.getCurrentLoggedInUser();
		this.modifiedDate = new Date(System.currentTimeMillis());
	}

}
