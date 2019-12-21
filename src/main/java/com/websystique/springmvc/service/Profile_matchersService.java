package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Profile_matchers;

public interface Profile_matchersService {
	List<Profile_matchers> findall();
	List<Profile_matchers> findall(Integer idProfile);
	void save(Profile_matchers pm);
	void borrar(Profile_matchers pm);
	Profile_matchers findById(Integer id);
}
