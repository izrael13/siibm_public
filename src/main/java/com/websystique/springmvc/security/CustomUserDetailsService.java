package com.websystique.springmvc.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.UserProfile;
import com.websystique.springmvc.service.UserService;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	static final Logger logger =  Logger.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private UserService userService;
	
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String ssoId)
			throws UsernameNotFoundException {
		User user = userService.findBySSO(ssoId);
		
		if(user==null){
			logger.info("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		else
			logger.info("Login" + "User : {}"+user);
		
			return new org.springframework.security.core.userdetails.User(user.getSsoId(), user.getPassword(), 
				 true, true, true, true, getGrantedAuthorities(user));
	}

	
	private List<GrantedAuthority> getGrantedAuthorities(User user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(UserProfile userProfile : user.getUserProfiles()){
			//logger.info("UserProfile : {}", userProfile);
			authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
		}
		//logger.info("authorities : {}", authorities);
		return authorities;
	}
	
}
