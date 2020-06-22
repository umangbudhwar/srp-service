package com.srp.data.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonAutoDetect
public class AuthenticationRequestDTO {

	/* Input structure for jwt based authentication method */
	
	private String userName;
    private String password;
 
}
