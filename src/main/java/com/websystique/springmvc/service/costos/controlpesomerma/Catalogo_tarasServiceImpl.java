package com.websystique.springmvc.service.costos.controlpesomerma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.costos.controlpesomerma.Catalogo_tarasDAO;
import com.websystique.springmvc.model.costos.controlpesomerma.Catalogo_taras;

@Service("catalogo_tarasService")
@Transactional
public class Catalogo_tarasServiceImpl implements Catalogo_tarasService{
	
	@Autowired
	Catalogo_tarasDAO dao;
	
	@Override
	public List<Catalogo_taras> ListaTaras() {
		return dao.ListaTaras();
	}

	@Override
	public Catalogo_taras BuscarxId(Integer Id) {
		return dao.BuscarxId(Id);
	}

}
