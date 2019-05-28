package com.websystique.springmvc.dao.tarjetas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.tarjetas.Catalogo_resistencias_sap_vw;

@Repository("catalogo_resistencias_sap_vwDAO")
public class Catalogo_resistencias_sap_vwImpl extends AbstractDao<Integer,Catalogo_resistencias_sap_vw> implements Catalogo_resistencias_sap_vwDAO{

	@Override
	public List<Catalogo_resistencias_sap_vw> ListaResis() {
		// FIXME Auto-generated method stub
		Map<String,String> mRes =  new HashMap<String, String>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("1", "resistencia");
		
		List<Catalogo_resistencias_sap_vw> ListaResis = criteriaQuery(mRes, mOrd);
		return ListaResis;
	}

	@Override
	public Catalogo_resistencias_sap_vw BuscarxId(Integer id) {
		// FIXME Auto-generated method stub
		return getByKey(id);
	}

}
