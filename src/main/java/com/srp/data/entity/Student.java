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
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "date_of_birth")
	private Date DOB;

	@Column(name = "age")
	private Integer age;

	@Column(name = "fee_receipt_number")
	private Long feeReceiptNumber;

	@Column(name = "enrollment_number")
	private String enrollmentNumber;

	@Column(name = "year_id")
	private Integer yearId;

	@Column(name = "college_year")
	private Integer collegeYear;

	@Column(name = "mother_name")
	private String motherName;

	@Column(name = "father_name")
	private String fatherName;

	@Column(name = "whatsapp_number")
	private Long whatsappNumber;

	@Column(name = "alternate_phone_number")
	private Long alternatePhoneNumber;

	@Column(name = "guardian_phone_number")
	private Long guardianPhoneNumber;

	@Column(name = "gender")
	private String gender;

	@Column(name = "category")
	private String category;

	@Column(name = "stream_id")
	private Integer streamId;

	@Column(name = "subjectId")
	private Integer[] subjectId;

	@Column(name = "group_division")
	private String groupDivision;

	@Column(name = "student_code")
	private String studentCode;
	
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
		this.yearId = LocalDate.now().getYear();
	}

	@PreUpdate
	public void preUpdate() {
		this.modifiedBy = SrpUtility.getCurrentLoggedInUser();
		this.modifiedDate = new Date(System.currentTimeMillis());
	}

}
