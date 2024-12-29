package com.demo.api.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.api.repo.UserRegistrationandloginRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRegistrationandloginRepository repo;
	@Override 
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user=repo.findByEmail(email);
		
		if(user==null) {
			throw new UsernameNotFoundException("User Not Found !!!!");
		} 
		return new CustomUserDetails(user);
	}

}
