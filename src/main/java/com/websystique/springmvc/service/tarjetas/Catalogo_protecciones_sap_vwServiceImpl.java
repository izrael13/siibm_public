package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.Catalogo_protecciones_sap_vwDAO;
import com.websystique.springmvc.model.tarjetas.Catalogo_protecciones_sap_vw;

@Service("catalogo_protecciones_sap_vwService")
@Transactional
public class Catalogo_protecciones_sap_vwServiceImpl implements Catalogo_protecciones_sap_vwService{
	
	@Autowired Catalogo_protecciones_sap_vwDAO dao;
	
	@Override
	public Catalogo_protecciones_sap_vw BuscaxId(Integer id) {
		return dao.BuscaxId(id);
	}

	@Override
	public List<Catalogo_protecciones_sap_vw> ListaProtecciones() {
		return dao.ListaProtecciones();
	}

}
