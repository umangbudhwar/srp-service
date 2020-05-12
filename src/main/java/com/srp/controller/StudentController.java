package com.srp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srp.data.dto.StudentDTO;
import com.srp.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("api/rest/srp/student")
@RestController
@Slf4j
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@PostMapping("/registerStudent")
	public StudentDTO addUser(@RequestBody StudentDTO newUser) {
		log.info("Student to be added : {}" , newUser);
		
		newUser = studentService.saveUser(newUser);
		log.info("Student added : {}" , newUser);
		return newUser;
	}
}
