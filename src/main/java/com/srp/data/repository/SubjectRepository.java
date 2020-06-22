package com.srp.data.repository;

import org.springframework.stereotype.Repository;

import com.srp.configuration.jpa.SrpRepository;
import com.srp.data.entity.Subject;

@Repository

public interface SubjectRepository extends SrpRepository<Subject, Long> {

}
