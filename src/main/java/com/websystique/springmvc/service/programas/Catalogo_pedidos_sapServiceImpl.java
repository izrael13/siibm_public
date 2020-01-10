package com.websystique.springmvc.service.programas;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.programas.Catalogo_pedidos_sapDAO;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.programas.Catalogo_pedidos_sap;

@Service("catalogo_pedidos_sapService")
@Transactional
public class Catalogo_pedidos_sapServiceImpl implements Catalogo_pedidos_sapService{
	
	@Autowired
	Catalogo_pedidos_sapDAO dao;
	
	@Override
	public Catalogo_pedidos_sap BuscarxId(Integer id) {
		return dao.BuscarxId(id);
	}

	@Override
	public Catalogo_pedidos_sap BuscarxParamas(List<ParamsGeneral> Params) {
		return dao.BuscarxParamas(Params);
	}

	@Override
	public List<Catalogo_pedidos_sap> BuscarxParamas(List<ParamsGeneral> Params, Map<String, String> mOrd) {
		return dao.BuscarxParamas(Params, mOrd);
	}

}
