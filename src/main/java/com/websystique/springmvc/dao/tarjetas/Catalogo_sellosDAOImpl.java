package com.websystique.springmvc.dao.tarjetas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.tarjetas.Catalogo_sellos;

@Repository("catalogo_sellosDAO")
public class Catalogo_sellosDAOImpl extends AbstractDao<Integer,Catalogo_sellos> implements Catalogo_sellosDAO{

	@Override
	public List<Catalogo_sellos> ListaSellos() {
		// FIXME Auto-generated method stub
		Map<String,String> mRes =  new HashMap<String, String>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("1", "sellos");
		
		List<Catalogo_sellos> ListaSellos = criteriaQuery(mRes,mOrd);
		return ListaSellos;
	}

	@Override
	public Catalogo_sellos BuscarxId(Integer id) {
		// FIXME Auto-generated method stub
		return getByKey(id);
	}

}
