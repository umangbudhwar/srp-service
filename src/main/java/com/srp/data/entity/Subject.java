package com.srp.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subject")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

	@Id
	@Column(name = "subject_id")
	private Long subjectId;
	
	@Column(name = "subject_name")
	private String subjectName;
	
	@Column(name = "stream_id")
	private Long streamId;
}
