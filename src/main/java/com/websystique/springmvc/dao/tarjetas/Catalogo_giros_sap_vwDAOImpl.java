package com.websystique.springmvc.dao.tarjetas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.Catalogo_giros_sap_vw;

@Repository("catalogo_giros_sap_vwDAO")
public class Catalogo_giros_sap_vwDAOImpl extends AbstractDao<Integer,Catalogo_giros_sap_vw> implements Catalogo_giros_sap_vwDAO{

	@Override
	public List<Catalogo_giros_sap_vw> ListaGiros() {
		// FIXME Auto-generated method stub
				//Map<String,String> mRes =  new HashMap<String, String>();
				List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
				Map<String,String> mOrd =  new HashMap<String, String>();
				
				mOrd.put("1", "groupname");
				
				List<Catalogo_giros_sap_vw> ListaGiros = criteriaGeneralList(Params, mOrd);
				return ListaGiros;
	}

}
