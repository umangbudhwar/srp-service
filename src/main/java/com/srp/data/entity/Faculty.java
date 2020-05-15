package com.srp.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.srp.utility.SrpUtility;

import lombok.Data;

@Entity
@Table(name = "faculty")
@Data
public class Faculty {

	/*
	 * @GeneratedValue(strategy = GenerationType.IDENTITY)
	 * 
	 * @Column(name = "Faculty_id") private Long Faculty_id;
	 */

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

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "contact_number")
	private Long contactNumber;
	
	@Column(name = "stream_id")
	private Long streamId;
	

	@PrePersist
	public void prePersist() {
		this.createdDate = new Date(System.currentTimeMillis());
		this.modifiedDate = new Date(System.currentTimeMillis());
		this.modifiedBy = SrpUtility.getCurrentLoggedInUser();
		this.isActive = true;
		this.role = "ROLE_FACULTY";
	}

	@PreUpdate
	public void preUpdate() {
		this.modifiedBy = SrpUtility.getCurrentLoggedInUser();
		this.modifiedDate = new Date(System.currentTimeMillis());
	}
}
