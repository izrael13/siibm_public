package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.Catalogo_vendedores_sap_vwDAO;
import com.websystique.springmvc.model.tarjetas.Catalogo_vendedores_sap_vw;

@Service("catalogo_vendedores_sap_vwService")
@Transactional
public class Catalogo_vendedores_sap_vwServiceImpl implements Catalogo_vendedores_sap_vwService{
	
	@Autowired
	Catalogo_vendedores_sap_vwDAO dao;
	
	@Override
	public List<Catalogo_vendedores_sap_vw> ListaVendedores() {
		// FIXME Auto-generated method stub
		return dao.ListaVendedores();
	}

	@Override
	public Catalogo_vendedores_sap_vw BuscarXid(Integer id) {
		// FIXME Auto-generated method stub
		return dao.BuscarXid(id);
	}

}
