package com.srp.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "subject")
@Data
public class Subject {

	@Id
	@Column(name = "subject_id")
	private Integer subjectId;
	
	@Column(name = "subject_name")
	private String subjectName;
	
	@Column(name = "stream_id")
	private Integer streamId;
}
