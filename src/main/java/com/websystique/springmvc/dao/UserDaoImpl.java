package com.websystique.springmvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.User;



@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
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
		Map<String,String> mRes =  new HashMap<String, String>();
		mRes.put("ssoId",sso);
		User user =  (User) criteriaQueryEqObj(mRes);
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public List<User> findAllUsers() {
		Map<String,String> mRes =  new HashMap<String, String>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		List<User> users = criteriaQuery(mRes,mOrd);
		return users;
	}

	public void save(User user) {
		persist(user);
	}

	public void deleteBySSO(String sso) {
		Map<String,String> mRes =  new HashMap<String, String>();
		mRes.put("ssoId",sso);
		User user =  (User) criteriaQueryEqObj(mRes);
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
		Map<String,String> mRes =  new HashMap<String, String>();
		mRes.put("ssoId",sso);
		mRes.put("email",email);
		User user =  (User) criteriaQueryEqObj(mRes);
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findMatcher(String sso) {

		String sql_query = "select ROW_NUMBER() OVER(ORDER BY TYPE ASC) AS ID, \r\n" + 
				"TYPE PROFILE_NAME, MATCHER MATCHER_NAME \r\n" + 
				"from PROFILE_MATCHERS a \r\n" + 
				"inner join USER_PROFILE b on a.ID_PROFILE = b.ID ";
		/*
		 *String sql_query = "select ROW_NUMBER() OVER(ORDER BY TYPE ASC) AS ID,b.type,a.url,a.nivel  \r\n" + 
				"from menus a\r\n" + 
				"inner join user_profile b on a.perfil_acceso = b.id;";
		 * */
				//"where SSO_ID = '"+sso+"'";
		Query query = getSession().createNativeQuery(sql_query);
		//query.setParameter(0, sso);
		//System.out.println(Results.size());
		List<Object> result = (List<Object>)query.getResultList();
		
		/*Iterator<Object> itr = result.iterator();
		ms = new MATCHERS_SECURITY();
		
		//System.out.println(result.size());
		while(itr.hasNext()){
						
		   Object[] obj = (Object[]) itr.next();
		   ms.setID(Integer.parseInt(String.valueOf(obj[0])));
		   ms.setPROFILE_NAME(String.valueOf(obj[1]));
		   ms.setMATCHER_NAME(String.valueOf(obj[2]));
		   //results.add(ms);
		}*/
		//System.out.println(results.size());
		return result;
	}
	
	
}
