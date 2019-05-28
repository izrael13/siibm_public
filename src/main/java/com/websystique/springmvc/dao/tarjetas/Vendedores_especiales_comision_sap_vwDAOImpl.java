package com.websystique.springmvc.dao.tarjetas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.tarjetas.Vendedores_especiales_comision_sap_vw;

@Repository("vendedores_especiales_comision_sap_vwDAO")
public class Vendedores_especiales_comision_sap_vwDAOImpl extends AbstractDao<Integer, Vendedores_especiales_comision_sap_vw> implements Vendedores_especiales_comision_sap_vwDAO{

	@Override
	public List<Vendedores_especiales_comision_sap_vw> VenEsp(Integer SlpCode, String CardCode) {
		// FIXME Auto-generated method stub
		Map<String,Integer> mRes =  new HashMap<String, Integer>();
		Map<String,String> mResS =  new HashMap<String, String>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mRes.put("u_slpcode", SlpCode);
		mResS.put("u_cardcode", CardCode);
		
		List<Vendedores_especiales_comision_sap_vw> ListaVenEsp = criteriaQueryEqStrInt(mResS,mRes, mOrd);
		return ListaVenEsp;
	}

}
