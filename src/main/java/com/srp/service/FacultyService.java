package com.srp.service;

import org.springframework.stereotype.Service;

import com.srp.data.dto.FacultyDTO;
import com.srp.data.entity.Faculty;


@Service
public interface FacultyService extends BaseService<FacultyDTO, Faculty, String> {

	FacultyDTO saveUser(FacultyDTO newUser);

	Faculty findByUserName(String userName);

}