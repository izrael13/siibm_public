package com.websystique.springmvc.service.programas;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.programas.Programas_ctiDAO;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.programas.Programas_cti;

@Service("programas_ctiService")
@Transactional
public class Programas_ctiServiceImpl implements Programas_ctiService{
	
	@Autowired Programas_ctiDAO dao;
	
	@Override
	public Programas_cti BuscarxId(Integer id) {
		return dao.BuscarxId(id);
	}

	@Override
	public Programas_cti BuscarxParams(List<ParamsGeneral> Params) {
		return dao.BuscarxParams(Params);
	}

	@Override
	public List<Programas_cti> BuscarxParams(List<ParamsGeneral> Params, Map<String, String> mOrd) {
		return dao.BuscarxParams(Params, mOrd);
	}

	@Override
	public List<Programas_cti> BuscarxParams(Integer Pedido, String TFSAP){ 
		return dao.BuscarxParams(Pedido, TFSAP);
	}

}
