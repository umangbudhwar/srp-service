package com.srp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.srp.configuration.jpa.SrpRepository;
import com.srp.data.dto.StudentDTO;
import com.srp.data.entity.Student;
import com.srp.data.entity.User;
import com.srp.data.repository.StudentRepository;
import com.srp.data.repository.UserRepository;
import com.srp.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentServiceImpl extends BaseServiceImpl<StudentDTO, Student, String> implements StudentService {

	public StudentServiceImpl(SrpRepository<Student, String> repository) {
		super(repository);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public StudentDTO saveUser(StudentDTO newUser) {
		log.info("In Student service Impl");
		Student student = getMapper().map(newUser, Student.class);
		student.setPassword(passwordEncoder.encode(student.getPassword()));
		student.setStudent_code(student.getYear_id()+student.getFirst_name()+student.getCollege_year());
		student = studentRepository.save(student);
		
		log.info("User saved in Student.");
		
		User user = getMapper().map(newUser, User.class);
		user.setRole("ROLE_STUDENT");
		user = userRepository.save(user);
		
		log.info("User saved in User table.");
		
		return getMapper().map(student, StudentDTO.class);
	}

	

}
