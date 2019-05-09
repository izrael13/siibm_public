package com.websystique.springmvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Catalogo_estados;

@Repository("catalogo_estadosDAO")
public class Catalogo_estadosDAOImpl extends AbstractDao<Integer, Catalogo_estados>implements Catalogo_estadosDAO{

	@Override
	public List<Catalogo_estados> ListEstados() {
		// FIXME Auto-generated method stub
		Map<String,String> mRes =  new HashMap<String, String>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		mOrd.put("1", "nombre");
		return (List<Catalogo_estados>)criteriaQuery(mRes,mOrd);
	}

	@Override
	public Catalogo_estados BuscarEdoId(Integer id) {
		// FIXME Auto-generated method stub
		Catalogo_estados catalogo_estados = getByKey(id);
		return catalogo_estados;
	}

}
