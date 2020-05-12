package com.srp.data.repository;

import org.springframework.stereotype.Repository;

import com.srp.configuration.jpa.SrpRepository;
import com.srp.data.dto.FacultyDTO;
import com.srp.data.entity.Faculty;

@Repository
public interface FacultyRepository extends SrpRepository<Faculty, String>{

	FacultyDTO save(FacultyDTO newUser);

//	Faculty findByUserName(String userName);

}
