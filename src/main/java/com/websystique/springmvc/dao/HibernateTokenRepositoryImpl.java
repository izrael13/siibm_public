package com.websystique.springmvc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.PersistentLogin;

@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenRepositoryImpl extends AbstractDao<String, PersistentLogin>
		implements PersistentTokenRepository {

	static final Logger logger = LoggerFactory.getLogger(HibernateTokenRepositoryImpl.class);

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		//logger.info("Creating Token for user : {}", token.getUsername());
		PersistentLogin persistentLogin = new PersistentLogin();
		persistentLogin.setUsername(token.getUsername());
		persistentLogin.setSeries(token.getSeries());
		persistentLogin.setToken(token.getTokenValue());
		persistentLogin.setLast_used(token.getDate());
		
		removeUserTokens(token.getUsername());
		persist(persistentLogin);

	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		//logger.info("Fetch Token if any for seriesId : {}", seriesId);
		try {
			/*Map<String,String> mRes =  new HashMap<String, String>();
			mRes.put("series",seriesId);*/
			List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
			Params.add(new ParamsGeneral(1,"series",seriesId,"EQ"));
			PersistentLogin persistentLogin = (PersistentLogin) criteriaGeneralObj(Params);
			
			return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
					persistentLogin.getToken(), persistentLogin.getLast_used());
		} catch (Exception e) {
			logger.info("Token not found...");
			return null;
		}
	}

	@Override
	public void removeUserTokens(String username) {
		//logger.info("Removing Token if any for user : {}", username);
		/*Map<String,String> mRes =  new HashMap<String, String>();
		mRes.put("username",username);*/
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"username",username,"EQ"));
		PersistentLogin persistentLogin = (PersistentLogin) criteriaGeneralObj(Params);
		
		/*Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));
		PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult(); */
		if (persistentLogin != null) {
			//logger.info("rememberMe was selected");
			delete(persistentLogin);
		}

	}

	@Override
	public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
		//logger.info("Updating Token for seriesId : {}", seriesId);
		PersistentLogin persistentLogin = getByKey(seriesId);
		persistentLogin.setToken(tokenValue);
		persistentLogin.setLast_used(lastUsed);
		update(persistentLogin);
	}

}
