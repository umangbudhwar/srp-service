package com.srp.service;

import org.springframework.stereotype.Service;

import com.srp.data.dto.StudentDTO;
import com.srp.data.entity.Student;

@Service
public interface StudentService extends BaseService<StudentDTO, Student, String> {

	StudentDTO saveUser(StudentDTO newUser);

}
