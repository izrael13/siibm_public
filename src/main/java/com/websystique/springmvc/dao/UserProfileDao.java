package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findById(int id);
	
	void save(UserProfile up);
}
