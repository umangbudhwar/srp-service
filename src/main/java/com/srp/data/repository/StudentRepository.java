package com.srp.data.repository;

import org.springframework.stereotype.Repository;

import com.srp.configuration.jpa.SrpRepository;
import com.srp.data.entity.Student;

@Repository
public interface StudentRepository extends SrpRepository<Student, String> {

	public int countByFirstName(String firstName);

}
