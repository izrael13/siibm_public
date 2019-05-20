package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.Catalogo_direcciones_sap_vwDAO;
import com.websystique.springmvc.model.tarjetas.Catalogo_direcciones_sap_vw;

@Service("catalogo_direcciones_sap_vwService")
@Transactional
public class Catalogo_direcciones_sap_vwServiceImpl implements Catalogo_direcciones_sap_vwService{
	
	@Autowired
	Catalogo_direcciones_sap_vwDAO dao;
	
	@Override
	public List<Catalogo_direcciones_sap_vw> ListaDir() {
		// FIXME Auto-generated method stub
		return dao.ListaDir();
	}

	@Override
	public List<Catalogo_direcciones_sap_vw> ListaDirCardCode(String CardCode) {
		// FIXME Auto-generated method stub
		return dao.ListaDirCardCode(CardCode);
	}

	@Override
	public List<Catalogo_direcciones_sap_vw> ListaDirCardCodeNumLine(String CardCode, Integer NumLine) {
		// FIXME Auto-generated method stub
		return dao.ListaDirCardCodeNumLine(CardCode, NumLine);
	}

}
