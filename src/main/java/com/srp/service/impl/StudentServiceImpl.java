package com.srp.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.srp.configuration.jpa.SrpRepository;
import com.srp.data.dto.StudentDTO;
import com.srp.data.entity.Student;
import com.srp.data.entity.User;
import com.srp.data.repository.StudentRepository;
import com.srp.data.repository.UserRepository;
import com.srp.service.StreamsService;
import com.srp.service.StudentService;
import com.srp.service.SubjectService;

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
	
	@Autowired
	StreamsService  streamService;
	
	@Autowired
	SubjectService  subjectService;
	
	@Override
	public StudentDTO registerStudent(StudentDTO newUser) {
		log.info("In Student service Impl");
		Student student = null;
		int firstNameUniqueCount = 0;
		Integer yearId = (LocalDate.now().getYear())%100;
		
		try {
			
			if(newUser.getStudentOTP() == 456789)
			{
				student = getMapper().map(newUser, Student.class);
				student.setPassword(passwordEncoder.encode(student.getPassword()));
				
				firstNameUniqueCount = studentRepository.countByFirstName(student.getFirstName());
				if(firstNameUniqueCount <= 1) {
					
					// setting student code without Father's name
					student.setStudentCode(yearId+newUser.getFirstName()+newUser.getCollegeYear());
				}
				else {
					// setting student code with Father's name
					student.setStudentCode(yearId+newUser.getFirstName()+newUser.getFatherName()+newUser.getCollegeYear());
				}
				
//				student.setStreamId(streamService.getStreamIdFromStreamName(newUser.getStreamName()));
				
				student = studentRepository.save(student);
				
				log.info("User saved in Student.");
				
				User user = getMapper().map(newUser, User.class);
				user.setRole("ROLE_STUDENT");
				user = userRepository.save(user);
				
				log.info("User saved in User table.");
			}
			
		}
		catch (Exception e) {
			
			log.error("Exception in Student Service Impl: saveUser(): {} " , e.getMessage());
		}
		return getMapper().map(student, StudentDTO.class);
	}

	

}
