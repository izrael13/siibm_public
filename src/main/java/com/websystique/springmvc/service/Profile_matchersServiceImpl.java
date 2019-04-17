package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.Profile_matchersDAO;
import com.websystique.springmvc.model.Profile_matchers;

@Service("profile_matchersService")
@Transactional
public class Profile_matchersServiceImpl implements Profile_matchersService{
	
	@Autowired
	private Profile_matchersDAO dao;
	
	@Override
	public List<Profile_matchers> findall() {
		// FIXME Auto-generated method stub
		return dao.findall();
	}

	@Override
	public void save(Profile_matchers pm) {
		dao.save(pm);
		
	}

	@Override
	public void borrar(Profile_matchers pm) {
		dao.borrar(pm);
		
	}

	@Override
	public Profile_matchers findById(Integer id) {
		// FIXME Auto-generated method stub
		return dao.findById(id);
	}

}
