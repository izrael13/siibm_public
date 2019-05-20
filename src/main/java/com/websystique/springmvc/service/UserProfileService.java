package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.UserProfile;


public interface UserProfileService {

	UserProfile findById(int id);
	
	List<UserProfile> findAll();
	
	void save(UserProfile up);
	
}
