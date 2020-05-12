package com.srp.data.dto;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.srp.data.entity.User;

import lombok.Data;

@Data
public class MyUserDetails implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8876276749311001273L;
	//String ROLE_PREFIX = "ROLE_";
	private String username;
	private String password;
	private String emailId;
	private boolean isactive;
	private String firstName;
	private String lastName;
	private List<GrantedAuthority> authorities;

	public MyUserDetails(){	
	}
	
	public MyUserDetails(User user){
		this.username = user.getUserName(); 
		this.password = user.getPassword();
		this.emailId = user.getEmailId();
		this.isactive = user.isActive();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		String roleDesc = user.getRole();
		this.authorities = Arrays.stream(roleDesc.split(","))
		.map(SimpleGrantedAuthority :: new)
		.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isactive;
	}
}
