package com.websystique.springmvc.service;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.UserDao;
import com.websystique.springmvc.model.User;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public User findById(int id) {
		return dao.findById(id);
	}

	public User findBySSO(String sso) {
		User user = dao.findBySSO(sso);
		return user;
	}

	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if(entity!=null){
			entity.setSsoId(user.getSsoId());
			if(!user.getPassword().equals(entity.getPassword())){
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
			entity.setCvevendedor_sap(user.getCvevendedor_sap());
			//entity.setCardcode_sap(user.getCardcode_sap());
		}
	}

	
	public void deleteUserBySSO(String sso) {
		dao.deleteBySSO(sso);
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public boolean isUserSSOUnique(Integer id, String sso) {
		User user = findBySSO(sso);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}	

	@Override
	public void updatePass(String u, String p) {
		Decoder decoder = Base64.getDecoder();
		byte[] decodedByte = decoder.decode(p);
		String decodedString = new String(decodedByte);
		dao.updatePass(u, passwordEncoder.encode(decodedString));		
	}

	@Override
	public void updateUserByUser(User user) {
		dao.updateUserByUser(user);		
	}

	@Override
	public User findBySSOnEmail(String sso, String email) {
		// FIXME Auto-generated method stub
		return dao.findBySSOnEmail(sso, email);
	}

	@Override
	public List<JSONObject> findMatcher() {
		// FIXME Auto-generated method stub
		return dao.findMatcher();
	}

	@Override
	public List<User> findCveVenUsers(Integer cveven) {
		// TODO Auto-generated method stub
		return dao.findCveVenUsers(cveven);
	}
	
}
