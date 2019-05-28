package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.Catalogo_especialidades_sap_vwDAO;
import com.websystique.springmvc.model.tarjetas.Catalogo_especialidades_sap_vw;

@Service("catalogo_especialidades_sap_vwService")
@Transactional
public class Catalogo_especialidades_sap_vwServiceImpl implements Catalogo_especialidades_sap_vwService{
	
	@Autowired
	Catalogo_especialidades_sap_vwDAO dao;
	
	@Override
	public List<Catalogo_especialidades_sap_vw> ListaEsp() {
		// FIXME Auto-generated method stub
		return dao.ListaEsp();
	}

	@Override
	public Catalogo_especialidades_sap_vw BuscaxId(Integer id) {
		// FIXME Auto-generated method stub
		return dao.BuscaxId(id);
	}

}
