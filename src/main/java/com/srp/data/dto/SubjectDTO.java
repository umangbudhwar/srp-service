package com.srp.data.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Data
@JsonAutoDetect
public class SubjectDTO {
	
	private Long subjectId;
	private String subjectName;
	private Long streamId;
	
}
