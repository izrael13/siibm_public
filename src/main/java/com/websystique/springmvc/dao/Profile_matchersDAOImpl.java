package com.websystique.springmvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.Profile_matchers;

@Repository("profile_matchersDAO")
public class Profile_matchersDAOImpl extends AbstractDao<Integer, Profile_matchers>implements Profile_matchersDAO{

	@Override
	public List<Profile_matchers> findall() {
		// FIXME Auto-generated method stub
		//Map<String,String> mRes =  new HashMap<String, String>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		mOrd.put("1", "ID_PROFILE");
		return (List<Profile_matchers>)criteriaGeneralList(Params, mOrd);
	}

	@Override
	public void save(Profile_matchers pm) {
		persist(pm);
	}
	
	@Override
	public void borrar(Profile_matchers pm) {
		delete(pm);
	}

	@Override
	public Profile_matchers findById(Integer id) {
		// FIXME Auto-generated method stub
		return getByKey(id);
	}

}
