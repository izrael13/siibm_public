package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.dao.tarjetas.Vendedores_especiales_comision_sap_vwDAO;
import com.websystique.springmvc.model.tarjetas.Vendedores_especiales_comision_sap_vw;

@Service("vendedores_especiales_comision_sap_vwService")
@Transactional
public class Vendedores_especiales_comision_sap_vwServiceImpl implements Vendedores_especiales_comision_sap_vwService{
	
	@Autowired
	Vendedores_especiales_comision_sap_vwDAO dao;
	
	@Override
	public List<Vendedores_especiales_comision_sap_vw> VenEsp(Integer SlpCode, String CardCode) {
		// FIXME Auto-generated method stub
		return dao.VenEsp(SlpCode, CardCode);
	}

}
