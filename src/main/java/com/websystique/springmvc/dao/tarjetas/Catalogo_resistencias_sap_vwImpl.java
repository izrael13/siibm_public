package com.websystique.springmvc.dao.tarjetas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.Catalogo_resistencias_sap_vw;

@Repository("catalogo_resistencias_sap_vwDAO")
public class Catalogo_resistencias_sap_vwImpl extends AbstractDao<Integer,Catalogo_resistencias_sap_vw> implements Catalogo_resistencias_sap_vwDAO{

	@Override
	public List<Catalogo_resistencias_sap_vw> ListaResis() {
		// FIXME Auto-generated method stub
		//Map<String,String> mRes =  new HashMap<String, String>();
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("1", "resistencia");
		
		List<Catalogo_resistencias_sap_vw> ListaResis = criteriaGeneralList(Params, mOrd);
		return ListaResis;
	}

	@Override
	public Catalogo_resistencias_sap_vw BuscarxId(Integer id) {
		// FIXME Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public List<Catalogo_resistencias_sap_vw> ListaResis(String corrugado) {
		// TODO Auto-generated method stub
		//Map<String,String> mResStr =  new HashMap<String, String>();
		//Map<String,Integer> mResInt =  new HashMap<String, Integer>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"corrugado",corrugado,"EQ"));
		//mResStr.put("corrugado", corrugado);
		mOrd.put("1", "resistencia");
		
		List<Catalogo_resistencias_sap_vw> ListaResis = criteriaGeneralList(Params, mOrd);
		return ListaResis;
	}

}
