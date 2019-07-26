package com.websystique.springmvc.dao.tarjetas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.Catalogo_colores;

@Repository("catalogo_coloresDAO")
public class Catalogo_coloresDAOImpl  extends AbstractDao<Integer,Catalogo_colores> implements Catalogo_coloresDAO{

	@Override
	public List<Catalogo_colores> ListaColores() {
		// FIXME Auto-generated method stub
		//Map<String,String> mRes =  new HashMap<String, String>();
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("1", "color");
		
		List<Catalogo_colores> ListaColores = criteriaGeneralList(Params, mOrd);
		return ListaColores;
	}

}
