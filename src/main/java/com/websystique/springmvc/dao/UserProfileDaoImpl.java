package com.websystique.springmvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.websystique.springmvc.model.UserProfile;



@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile>implements UserProfileDao{

	public UserProfile findById(int id) {
		return getByKey(id);
	}
	
	public List<UserProfile> findAll(){
		Map<String,String> mRes =  new HashMap<String, String>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		mOrd.put("1", "type");
		return (List<UserProfile>)criteriaQuery(mRes,mOrd);
	}

	@Override
	public void save(UserProfile up) {
		persist(up);
	}
	
}
