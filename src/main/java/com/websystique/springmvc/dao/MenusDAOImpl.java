package com.websystique.springmvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Menus;

@Repository("menusDAO")
public class MenusDAOImpl extends AbstractDao<Integer, Menus>implements MenusDAO{

	@Override
	public List<Menus> ListaMenusxpadre(Integer padre) {
		// FIXME Auto-generated method stub
		Map<String,Integer> mRes =  new HashMap<String, Integer>();
		Map<String,String> mOrd =  new HashMap<String, String>();

		mRes.put("padre", padre);
		return (List<Menus>)criteriaQueryEqInt(mRes,mOrd);
	}

	@Override
	public List<Menus> ListaMenusxpadre() {
		// TODO Auto-generated method stub
		Map<String,String> mRes =  new HashMap<String, String>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("1", "id");
		
		return (List<Menus>)criteriaQuery(mRes,mOrd);
	}

}
