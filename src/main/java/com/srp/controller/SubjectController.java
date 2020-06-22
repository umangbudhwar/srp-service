package com.srp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srp.data.dto.SubjectDTO;
import com.srp.service.SubjectService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("api/rest/srp/subject")
@RestController
@Slf4j
public class SubjectController {
	
	@Autowired
	SubjectService subjectService;
	
	@GetMapping("/getSubjects")
	public ResponseEntity<List<SubjectDTO>> getSubjects() {
		// log.info("In Subject Controller: getSubjects()");
		List<SubjectDTO> subjectDto = subjectService.getSubjects();

		return ResponseEntity.ok(subjectDto);
	}
	
	
}
