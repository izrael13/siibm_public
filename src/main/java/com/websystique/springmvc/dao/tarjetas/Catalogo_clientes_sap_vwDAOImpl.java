package com.websystique.springmvc.dao.tarjetas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.tarjetas.Catalogo_clientes_sap_vw;

@Repository("catalogo_clientes_sap_vwDAO")
public class Catalogo_clientes_sap_vwDAOImpl extends AbstractDao<Integer,Catalogo_clientes_sap_vw> implements Catalogo_clientes_sap_vwDAO{

	@Override
	public List<Catalogo_clientes_sap_vw> ListaCtes(Integer SlpCode) {
		// FIXME Auto-generated method stub
		
		Map<String,Integer> mRes =  new HashMap<String, Integer>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mRes.put("slpcode", SlpCode);
		
		mOrd.put("1", "cardname");
		
		List<Catalogo_clientes_sap_vw> ListaCtes = criteriaQueryEqInt(mRes, mOrd);
		return ListaCtes;
	
	}

	@Override
	public List<Catalogo_clientes_sap_vw> ListaCtes() {
		// FIXME Auto-generated method stub
		Map<String,String> mRes =  new HashMap<String, String>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("1", "cardname");
		
		List<Catalogo_clientes_sap_vw> ListaCtes = criteriaQuery(mRes, mOrd);
		return ListaCtes;
	}

	@Override
	public Catalogo_clientes_sap_vw cat_cte_sap(String CardCode) {
		// FIXME Auto-generated method stub
		Map<String,String> mRes =  new HashMap<String, String>();
		mRes.put("cardcode",CardCode);
		Catalogo_clientes_sap_vw ctes =  (Catalogo_clientes_sap_vw) criteriaQueryEqObj(mRes);
		return ctes;
	}

}
