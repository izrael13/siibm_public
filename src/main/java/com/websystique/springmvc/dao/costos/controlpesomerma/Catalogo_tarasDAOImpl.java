package com.websystique.springmvc.dao.costos.controlpesomerma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.costos.controlpesomerma.Catalogo_taras;

@Repository("catalogo_tarasDAO")
public class Catalogo_tarasDAOImpl extends AbstractDao<Integer, Catalogo_taras> implements Catalogo_tarasDAO{

	@Override
	public List<Catalogo_taras> ListaTaras() {
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("1", "descripcion");
		
		List<Catalogo_taras> ListaTaras = criteriaGeneralList(Params, mOrd);
		return ListaTaras;
	}

	@Override
	public Catalogo_taras BuscarxId(Integer Id) {
		return getByKey(Id);
	}

}
