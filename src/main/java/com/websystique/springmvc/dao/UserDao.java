package com.websystique.springmvc.dao;

import java.util.List;

import org.json.JSONObject;

import com.websystique.springmvc.model.User;


public interface UserDao {

	User findById(int id);
	
	User findBySSO(String sso);
	
	void save(User user);
	
	void deleteBySSO(String sso);
	
	List<User> findAllUsers();
	
	void updatePass(String u, String p);
	
	void updateUserByUser(User user);
	
	User findBySSOnEmail(String sso, String email);
	
	List<JSONObject> findMatcher(String sso);
	
}

