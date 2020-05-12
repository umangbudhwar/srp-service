package com.srp.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthenticationRequestDTO {

	/* Input structure for jwt based authentication method */
	
	private String userName;
    private String password;
 
}
