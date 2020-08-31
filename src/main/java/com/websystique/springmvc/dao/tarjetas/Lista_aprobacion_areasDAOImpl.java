package com.websystique.springmvc.dao.tarjetas;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.Lista_aprobacion_areas;

@Repository("lista_aprobacion_areasDAO")
public class Lista_aprobacion_areasDAOImpl extends AbstractDao<Integer, Lista_aprobacion_areas> implements Lista_aprobacion_areasDAO{

	@Override
	public Lista_aprobacion_areas BuscarXKey(Integer id) {
		return getByKey(id);
	}

	@Override
	public List<Lista_aprobacion_areas> ListaAproba(List<ParamsGeneral> params, Map<String, String> mOrd) {
		return criteriaGeneralList(params, mOrd);
	}

}
