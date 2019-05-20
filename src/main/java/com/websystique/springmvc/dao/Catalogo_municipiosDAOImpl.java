package com.websystique.springmvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Catalogo_municipios;

@Repository("catalogo_municipiosDAO")
public class Catalogo_municipiosDAOImpl extends AbstractDao<Integer,Catalogo_municipios> implements Catalogo_municipiosDAO{

	@Override
	public List<Catalogo_municipios> ListMunicipiosXEstado(Integer cve_estado) {
		// FIXME Auto-generated method stub
		Map<String,Integer> mRes =  new HashMap<String, Integer>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("1", "nombre");
		mRes.put("id_estado",cve_estado);
		
		return (List<Catalogo_municipios>)criteriaQueryEqInt(mRes,mOrd);
	}

	@Override
	public List<Catalogo_municipios> ListMunicipios() {
		// FIXME Auto-generated method stub
		Map<String,String> mRes =  new HashMap<String, String>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("1", "nombre");
		
		return (List<Catalogo_municipios>)criteriaQuery(mRes,mOrd);
	}


}
