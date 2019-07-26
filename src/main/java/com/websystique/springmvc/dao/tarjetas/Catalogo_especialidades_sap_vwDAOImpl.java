package com.websystique.springmvc.dao.tarjetas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.Catalogo_especialidades_sap_vw;

@Repository("catalogo_especialidades_sap_vwDAO")
public class Catalogo_especialidades_sap_vwDAOImpl extends AbstractDao<Integer,Catalogo_especialidades_sap_vw> implements Catalogo_especialidades_sap_vwDAO{

	@Override
	public List<Catalogo_especialidades_sap_vw> ListaEsp() {
		// FIXME Auto-generated method stub
		//Map<String,String> mRes =  new HashMap<String, String>();
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("1", "name");
		
		List<Catalogo_especialidades_sap_vw> ListaEsp = criteriaGeneralList(Params, mOrd);
		
		return ListaEsp;
	}

	@Override
	public Catalogo_especialidades_sap_vw BuscaxId(Integer id) {
		// FIXME Auto-generated method stub
		return getByKey(id);
	}

}
