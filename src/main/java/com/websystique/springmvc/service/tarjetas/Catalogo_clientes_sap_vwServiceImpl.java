package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.Catalogo_clientes_sap_vwDAO;
import com.websystique.springmvc.model.tarjetas.Catalogo_clientes_sap_vw;

@Service("catalogo_clientes_sap_vwService")
@Transactional
public class Catalogo_clientes_sap_vwServiceImpl implements Catalogo_clientes_sap_vwService{
	
	@Autowired
	Catalogo_clientes_sap_vwDAO dao;
	
	@Override
	public List<Catalogo_clientes_sap_vw> ListaCtes() {
		// FIXME Auto-generated method stub
		return dao.ListaCtes();
	}

	@Override
	public List<Catalogo_clientes_sap_vw> ListaCtes(Integer SlpCode) {
		// FIXME Auto-generated method stub
		return dao.ListaCtes(SlpCode);
	}

	@Override
	public Catalogo_clientes_sap_vw cat_cte_sap(String CardCode) {
		// FIXME Auto-generated method stub
		return dao.cat_cte_sap(CardCode);
	}

}
