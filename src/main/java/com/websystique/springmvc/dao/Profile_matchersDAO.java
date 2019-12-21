package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Profile_matchers;

public interface Profile_matchersDAO {
	List<Profile_matchers> findall();
	List<Profile_matchers> findall(Integer idProfile);
	void save(Profile_matchers pm);
	void borrar(Profile_matchers pm);
	Profile_matchers findById(Integer id);
}
