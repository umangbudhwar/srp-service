package com.srp.service.impl;

import org.springframework.stereotype.Service;

import com.srp.config.jpa.SrpRepository;
import com.srp.data.dto.StudentDTO;
import com.srp.data.entity.Student;
import com.srp.service.StudentService;

@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentDTO, Student, Long> implements StudentService {

	public StudentServiceImpl(SrpRepository<Student, Long> repository) {
		super(repository);
	}

}
