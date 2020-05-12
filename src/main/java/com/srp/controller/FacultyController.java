package com.srp.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public FacultyDTO addUser(@RequestBody FacultyDTO newUser) {
		log.info("Faculty to be added : {}" , newUser);
		
		newUser = facultyService.saveUser(newUser);
		log.info("Faculty added : {}" , newUser);
		return newUser;
	}
}
