package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.Catalogo_bolsas_sap_vwDAO;
import com.websystique.springmvc.model.tarjetas.Catalogo_bolsas_sap_vw;

@Service("catalogo_bolsas_sap_vwService")
@Transactional
public class Catalogo_bolsas_sap_vwServiceImpl implements Catalogo_bolsas_sap_vwService{

	@Autowired
	Catalogo_bolsas_sap_vwDAO dao;
		
	@Override
	public List<Catalogo_bolsas_sap_vw> ListaBolsas() {
		// FIXME Auto-generated method stub
		return dao.ListaBolsas();
	}

}
