package com.websystique.springmvc.service;

import java.util.List;

import org.json.JSONObject;

import com.websystique.springmvc.model.User;


public interface UserService {
	
	User findById(int id);
	
	User findBySSO(String sso);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserBySSO(String sso);

	List<User> findAllUsers(); 
	
	boolean isUserSSOUnique(Integer id, String sso);
	
	void updatePass(String u, String p);
	
	void updateUserByUser(User user);
	
	User findBySSOnEmail(String sso, String email);
	
	List<JSONObject> findMatcher(String sso);
}