package com.websystique.springmvc.dao.tarjetas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.tarjetas.Comision_directo_sap_vw;

@Repository("comision_directo_sap_vwDAO")
public class Comision_directo_sap_vwDAOImpl extends AbstractDao<Integer,Comision_directo_sap_vw> implements Comision_directo_sap_vwDAO{

	@Override
	public List<Comision_directo_sap_vw> ListaCDSV() {
		// FIXME Auto-generated method stub
		Map<String,String> mRes =  new HashMap<String, String>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		List<Comision_directo_sap_vw> ListaCDSV = criteriaQuery(mRes, mOrd);
		
		return ListaCDSV;
	}

}
