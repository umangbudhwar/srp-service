package com.srp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.srp.configuration.jpa.SrpRepository;
import com.srp.data.dto.FacultyDTO;
import com.srp.data.entity.Faculty;
import com.srp.data.entity.User;
import com.srp.data.repository.FacultyRepository;
import com.srp.data.repository.UserRepository;
import com.srp.service.FacultyService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FacultyServiceImpl extends BaseServiceImpl<FacultyDTO, Faculty, String> 
									 implements FacultyService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	FacultyRepository FacultyRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public FacultyServiceImpl(SrpRepository<Faculty, String> repository) {
		super(repository);
	}
	
	@Override
	public FacultyDTO registerFaculty(FacultyDTO newUser) {
		
		log.info("In FacultyServiceImpl/saveUser(). ");
		Faculty faculty = null;
		
		try {
		
			if(newUser.getAdminOTP() == 123456) {
				faculty = getMapper().map(newUser, Faculty.class);
				faculty.setPassword(passwordEncoder.encode(faculty.getPassword()));
				faculty = FacultyRepository.save(faculty);
				
				log.info("User saved in Faculty.");
				
				User user = getMapper().map(newUser, User.class);
				user.setRole("ROLE_FACULTY");
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				user = userRepository.save(user);
				
				log.info("User saved in User table.");
			}
		}catch (Exception e) {
			log.error("Exception in Faculty Service Impl: saveUser(): {} " , e.getMessage());
		}
		
		return getMapper().map(faculty, FacultyDTO.class);
	}

	@Override
	public Faculty findByUserName(String userName) {
		log.info("In Faculty service:");
		Optional<Faculty> r = null;
		try {
			r = FacultyRepository.findById(userName);
		}catch (Exception e) {
			log.error("Exception in Faculty Service Impl: findByUserName (): {} " , e.getMessage());
		}
		return r.get();
	}

}
