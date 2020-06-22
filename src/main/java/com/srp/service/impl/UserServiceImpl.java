package com.srp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srp.configuration.jpa.SrpRepository;
import com.srp.data.dto.UserDTO;
import com.srp.data.entity.User;
import com.srp.data.repository.UserRepository;
import com.srp.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDTO, User, String> implements UserService {

	public UserServiceImpl(SrpRepository<User, String> repository) {
		super(repository);
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User findByUserName(String userName) {
		// log.info("In User service:");
		Optional<User> r = userRepository.findById(userName);
		return r.get();
	}

	

}
