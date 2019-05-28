package com.websystique.springmvc.dao.tarjetas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.tarjetas.Comision_comisionista_sap_vw;

@Repository("comision_comisionista_sap_vwDAO")
public class Comision_comisionista_sap_vwDAOImpl extends AbstractDao<Integer,Comision_comisionista_sap_vw> implements Comision_comisionista_sap_vwDAO{

	@Override
	public List<Comision_comisionista_sap_vw> ListaCCSV() {
		// FIXME Auto-generated method stub
		Map<String,String> mRes =  new HashMap<String, String>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		List<Comision_comisionista_sap_vw> ListaCCSV = criteriaQuery(mRes, mOrd);
		
		return ListaCCSV;
	}

}
