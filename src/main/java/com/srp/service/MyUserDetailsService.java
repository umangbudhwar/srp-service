package com.srp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.srp.data.dto.MyUserDetails;
import com.srp.data.entity.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
			
		// log.info("in MyUserDetailsService --> loadUserByUsername "); 
		User user = userService.findByUserName(userName);
		Optional<User> u = Optional.of(user);
		
		// log.info("Id {}",user.toString());
		u.orElseThrow(()->new UsernameNotFoundException("Not found : " + userName)); 
		
		return u.map(MyUserDetails :: new).get();
	}
}
