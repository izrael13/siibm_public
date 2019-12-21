package com.websystique.springmvc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.service.Profile_matchersService;
import com.websystique.springmvc.service.UserProfileService;



@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	Profile_matchersService pms;
	@Autowired
	UserProfileService ups;
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public User findById(int id) {
		User user = getByKey(id);
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public User findBySSO(String sso) {
		//logger.info("SSO : {}", sso);
		/*Map<String,String> mRes =  new HashMap<String, String>();
		mRes.put("ssoId",sso);*/
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"ssoId",sso,"EQ"));
		User user =  (User) criteriaGeneralObj(Params);
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public List<User> findAllUsers() {
		//Map<String,String> mRes =  new HashMap<String, String>();
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		List<User> users = criteriaGeneralList(Params, mOrd);
		return users;
	}

	public void save(User user) {
		persist(user);
	}

	public void deleteBySSO(String sso) {
		/*Map<String,String> mRes =  new HashMap<String, String>();
		mRes.put("ssoId",sso);*/
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"ssoId",sso,"EQ"));
		User user =  (User) criteriaGeneralObj(Params);
		delete(user);
	}

	@Override
	public void updatePass(String u, String p) {
		User user = findBySSO(u);
		user.setPassword(p);
		update(user);
	}

	@Override
	public void updateUserByUser(User user) {
		// FIXME Auto-generated method stub
		update(user);
	}

	@Override
	public User findBySSOnEmail(String sso, String email) {
		// FIXME Auto-generated method stub
		/*Map<String,String> mRes =  new HashMap<String, String>();
		mRes.put("ssoId",sso);
		mRes.put("email",email);*/
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"ssoId",sso,"EQ"));
		Params.add(new ParamsGeneral(1,"email",email,"EQ"));
		User user =  (User) criteriaGeneralObj(Params);
		return user;
	}

	@Override
	public List<JSONObject> findMatcher() {
		List<JSONObject> Lista = new ArrayList<JSONObject>();
		pms.findall().forEach(a ->{
			JSONObject JsonObj = new JSONObject();
			JsonObj.put("matcher", a.getMATCHER());
			JsonObj.put("profile", ups.findById(a.getID_PROFILE()).getType());
			Lista.add(JsonObj);
		});
		
		return Lista;
		
	}

	@Override
	public List<User> findCveVenUsers(Integer cveven) {
		Map<String,String> mOrd =  new HashMap<String, String>();
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"cvevendedor_sap",cveven,"EQ"));
		List<User> users = criteriaGeneralList(Params, mOrd);
		return users;
	}
	
	
}
