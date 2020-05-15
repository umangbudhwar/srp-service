package com.srp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srp.data.dto.FacultyDTO;
import com.srp.service.FacultyService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("api/rest/srp/faculty")
@RestController
@Slf4j
public class FacultyController {
	
	@Autowired
	FacultyService facultyService;
	
	@PostMapping("/registerFaculty")
	public ResponseEntity<FacultyDTO> registerFaculty(@RequestBody FacultyDTO facultyDto) {
		log.info("Faculty to be added : {}" , facultyDto);
		facultyDto = facultyService.registerFaculty(facultyDto);
		log.info("Faculty added : {}" , facultyDto);
		return ResponseEntity.ok(facultyDto);
	}
}
