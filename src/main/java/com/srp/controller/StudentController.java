package com.srp.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.srp.service.StudentService;

public class StudentController {
	
	@Autowired
	StudentService studentService;
}
