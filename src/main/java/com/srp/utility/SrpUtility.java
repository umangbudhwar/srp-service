package com.srp.utility;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SrpUtility {
	
private static JwtUtil jwtUtil;
	
	@Autowired
	private JwtUtil jwtUtilTemp;
	
	@PostConstruct
	public void init()
	{
		SrpUtility.jwtUtil = jwtUtilTemp;
	}
	
	public static String getCurrentLoggedInUser() {
		
		String loggedInUser = null;
		
		if(RequestContextHolder.getRequestAttributes()!= null) {
			HttpServletRequest httpServletRequest = ((ServletRequestAttributes)RequestContextHolder
													.getRequestAttributes()).getRequest();
			String auhtorizationHeader = httpServletRequest.getHeader("Authorization");
			if(auhtorizationHeader!=null) {
				String token = auhtorizationHeader.substring(7);
				loggedInUser = jwtUtil.extractUsername(token);
			}
		}
		return loggedInUser;
	}
}
