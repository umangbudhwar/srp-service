package com.srp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.srp.data.dto.AuthenticationRequestDTO;
import com.srp.data.dto.AuthenticationResponseDTO;
import com.srp.data.dto.MyUserDetails;
import com.srp.service.MyUserDetailsService;
import com.srp.utility.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("api/rest/srp")
@RestController
@Slf4j
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	MyUserDetailsService userDetailsService;

	@Autowired
	JwtUtil jwtUtil;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<AuthenticationResponseDTO> createAuthenticationToken(
			@RequestBody AuthenticationRequestDTO authenticationRequest) throws Exception {
		
		AuthenticationResponseDTO authenticationResponseDTO;
		final MyUserDetails myUserDetails = (MyUserDetails)userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		try {
			// log.info("Authenticating User :");
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
			// log.info("User Authenticated :");
		} catch (Exception e) {
			// log.error("{}", e);
			throw new Exception("Incorrect Username or Passowrd", e);
		}

		final String jwt = jwtUtil.generateToken(myUserDetails);
		
		authenticationResponseDTO = new AuthenticationResponseDTO(jwt, myUserDetails.getUsername(),
																  myUserDetails.getFirstName(),myUserDetails.getLastName(),
																  myUserDetails.getAuthorities().toString());
		
		return ResponseEntity.ok(authenticationResponseDTO);

	}

	@GetMapping("/home")
	public String home() {
		return "Home";
	}
}
