package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.Catalogo_resistencias_sap_vwDAO;
import com.websystique.springmvc.model.tarjetas.Catalogo_resistencias_sap_vw;

@Service("catalogo_resistencias_sap_vwService")
@Transactional
public class Catalogo_resistencias_sap_vwServiceImpl implements Catalogo_resistencias_sap_vwService{
	
	@Autowired
	Catalogo_resistencias_sap_vwDAO dao;
	
	@Override
	public List<Catalogo_resistencias_sap_vw> ListaResis() {
		// FIXME Auto-generated method stub
		return dao.ListaResis();
	}

	@Override
	public Catalogo_resistencias_sap_vw BuscarxId(Integer id) {
		// FIXME Auto-generated method stub
		return dao.BuscarxId(id);
	}

}
