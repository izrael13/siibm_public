package com.websystique.springmvc.service.tarjetas;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.Lista_aprobacion_areasDAO;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.Lista_aprobacion_areas;

@Service("lista_aprobacion_areasService")
@Transactional
public class Lista_aprobacion_areasServiceImpl implements Lista_aprobacion_areasService{
	
	@Autowired Lista_aprobacion_areasDAO dao;
	
	@Override
	public Lista_aprobacion_areas BuscarXKey(Integer id) {
		return dao.BuscarXKey(id);
	}

	@Override
	public List<Lista_aprobacion_areas> ListaAproba(List<ParamsGeneral> params, Map<String, String> mOrd) {
		return dao.ListaAproba(params, mOrd);
	}

}
