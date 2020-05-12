package com.srp.data.dto;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class AuthenticationResponseDTO implements Serializable {
	
	/* Output structure for jwt based authentication method */
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String jwt;
	private String userName;
	private String emailId;
	private String firstName;
	private String lastName;
	private String role;

    public AuthenticationResponseDTO(String jwt,String userName,String firstName, String lastName, 
    								 String role) {
        this.jwt = jwt;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

	/*
	 * public String getJwt() { return jwt; }
	 */
}