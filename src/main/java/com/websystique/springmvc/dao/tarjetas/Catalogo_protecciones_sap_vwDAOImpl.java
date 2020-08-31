package com.websystique.springmvc.dao.tarjetas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.Catalogo_protecciones_sap_vw;

@Repository("catalogo_protecciones_sap_vwDAO")
public class Catalogo_protecciones_sap_vwDAOImpl extends AbstractDao<Integer, Catalogo_protecciones_sap_vw> implements Catalogo_protecciones_sap_vwDAO{

	@Override
	public Catalogo_protecciones_sap_vw BuscaxId(Integer id) {
		return getByKey(id);
	}

	@Override
	public List<Catalogo_protecciones_sap_vw> ListaProtecciones() {
		List<ParamsGeneral> paramsGeneral = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		return criteriaGeneralList(paramsGeneral, mOrd);
	}

}
