package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.Catalogo_estadosDAO;
import com.websystique.springmvc.model.Catalogo_estados;

@Service("catalogo_estadosService")
@Transactional
public class Catalogo_estadosServiceImpl implements Catalogo_estadosService{
	
	@Autowired
	Catalogo_estadosDAO dao;
	
	@Override
	public List<Catalogo_estados> ListEstados() {
		// FIXME Auto-generated method stub
		return dao.ListEstados();
	}

	@Override
	public Catalogo_estados BuscarEdoId(Integer id) {
		// FIXME Auto-generated method stub
		return dao.BuscarEdoId(id);
	}

}
