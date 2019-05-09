package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.Catalogo_giros_sap_vwDAO;
import com.websystique.springmvc.model.tarjetas.Catalogo_giros_sap_vw;

@Service("catalogo_giros_sap_vwService")
@Transactional
public class Catalogo_giros_sap_vwServiceImpl implements Catalogo_giros_sap_vwService{
	
	@Autowired
	Catalogo_giros_sap_vwDAO dao;
	
	@Override
	public List<Catalogo_giros_sap_vw> ListaGiros() {
		// FIXME Auto-generated method stub
		return dao.ListaGiros();
	}

}
