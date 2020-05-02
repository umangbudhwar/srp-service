package com.srp.data.repository;

import org.springframework.stereotype.Repository;

import com.srp.config.jpa.SrpRepository;
import com.srp.data.entity.Student;

@Repository
public interface StudentRepository extends SrpRepository<Student, Long> {

}
