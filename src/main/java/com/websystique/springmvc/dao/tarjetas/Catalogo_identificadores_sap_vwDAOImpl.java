package com.websystique.springmvc.dao.tarjetas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.Catalogo_identificadores_sap_vw;

@Repository("catalogo_identificadores_sap_vwDAO")
public class Catalogo_identificadores_sap_vwDAOImpl extends AbstractDao<Integer, Catalogo_identificadores_sap_vw> implements Catalogo_identificadores_sap_vwDAO{

	@Override
	public Catalogo_identificadores_sap_vw BuscaxId(Integer id) {
		return getByKey(id);
	}

	@Override
	public List<Catalogo_identificadores_sap_vw> ListaIdentificadores() {
		List<ParamsGeneral> paramsGeneral = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		return criteriaGeneralList(paramsGeneral, mOrd);
	}

}
