package com.websystique.springmvc.dao.tarjetas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.Catalogo_bolsas_sap_vw;

@Repository("catalogo_bolsas_sap_vwDAO")
public class Catalogo_bolsas_sap_vwDAOImpl extends AbstractDao<Integer,Catalogo_bolsas_sap_vw> implements Catalogo_bolsas_sap_vwDAO{

	@Override
	public List<Catalogo_bolsas_sap_vw> ListaBolsas() {
		// FIXME Auto-generated method stub
		//Map<String,String> mRes =  new HashMap<String, String>();
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("1", "name");
		
		List<Catalogo_bolsas_sap_vw> ListaBolsas = criteriaGeneralList(Params, mOrd);
		return ListaBolsas;
	}

}
