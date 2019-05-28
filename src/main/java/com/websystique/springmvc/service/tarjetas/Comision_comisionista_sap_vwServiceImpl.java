package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.Comision_comisionista_sap_vwDAO;
import com.websystique.springmvc.model.tarjetas.Comision_comisionista_sap_vw;

@Service("comision_comisionista_sap_vwService")
@Transactional
public class Comision_comisionista_sap_vwServiceImpl implements Comision_comisionista_sap_vwService{
	
	@Autowired
	Comision_comisionista_sap_vwDAO dao;
	
	@Override
	public List<Comision_comisionista_sap_vw> ListaCCSV() {
		// FIXME Auto-generated method stub
		return dao.ListaCCSV();
	}

}
